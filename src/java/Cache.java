import com.articulate.sigma.*;
import com.articulate.sigma.trans.SUMOtoTFAform;
import com.articulate.sigma.utils.*;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.*;

import com.google.common.collect.Sets;
import com.google.common.collect.Maps;
import com.google.common.collect.Lists;

public class Cache implements Serializable {

    public KB kb = null;

    public static boolean debug = false;

    // The String constant that is the suffix for files of cached assertions.
    public static final String _cacheFileSuffix = "_Cache.kif";

    // all the relations in the kb
    public HashSet<String> relations = new HashSet<String>();

    // all the functions in the kb
    public HashSet<String> functions = new HashSet<String>();

    // all relations that are not functions
    public HashSet<String> predicates = new HashSet<>();

    // all the transitive relations in the kb
    public Set<String> transRels = new HashSet<String>();

    // all the transitive relations between instances in the kb that must have the same type
    public HashSet<String> instRels = new HashSet<String>();

    // all the transitive relations between instances in the kb
    public HashSet<String> instTransRels = new HashSet<String>();

    // all the transitive relations that are known to be appropriate to use at the time
    // this code was created - used to provide warnings
    public static final List<String> intendedTransRels =
            Arrays.asList("subclass", "subrelation", "subAttribute");

    /** All the cached "parent" relations of all transitive relations
     * meaning the relations between all first arguments and the
     * transitive closure of second arguments.  The external HashMap
     * pairs relation name String keys to values that are the parent
     * relationships.  The interior HashMap is the set of terms and
     * their transitive closure of parents.
     */
    public HashMap<String, HashMap<String, HashSet<String>>> parents =
            new HashMap<String, HashMap<String, HashSet<String>>>();

    /** Parent relations from instances, including those that are
     * transitive through (instance,instance) relations, such as
     * subAttribute and subrelation.  May not do what you think
     * since the key is the child (instance)
     */
    public HashMap<String, HashSet<String>> instanceOf =
            new HashMap<String, HashSet<String>>();

    // all the instances of a class key, including through subrelation
    // and subAttribute
    public HashMap<String, HashSet<String>> instances = new HashMap<>();

    /** A temporary list of instances built during creation of the
     * children map, in order to efficiently create the instances map
     **/
    public HashSet<String> insts = new HashSet<String>();

    /** All the cached "child" relations of all transitive relations
     * meaning the relations between all first arguments and the
     * transitive closure of second arguments.  The external HashMap
     * pairs relation name String keys to values that are the child
     * relationships.  The interior HashMap is the set of terms and
     * their transitive closure of children.
     */
    public Map<String, Map<String, Set<String>>> children =
            new HashMap<String, Map<String, Set<String>>>();

    /** Relation name keys and argument types with 0th arg always ""
     * except in the case of Functions where the 0th arg will be the
     * function range.
     * Variable arity relations may have a type for the last argument,
     * which will be the type repeated for all extended arguments.
     * Note that types can be functions, rather than just terms. Note that
     * types (when there's a domainSubclass etc) are designated by a
     * '+' appended to the class name.
     **/
    public HashMap<String, ArrayList<String>> signatures =
            new HashMap<String, ArrayList<String>>();

    // The number of arguments to each relation.  Variable arity is -1
    public HashMap<String, Integer> valences = new HashMap<String, Integer>();

    /** Disjoint relationships which were explicitly defined in "partition", "disjoint",
     * and "disjointDecomposition" expressions
     **/
    public HashMap<String, HashSet<String>> explicitDisjoint = new HashMap<>();

    // each pair of classes as class1\tclass2
    // transitive closure of classes based on explicitDisjoint
    public HashSet<String> disjoint = new HashSet<>();

    // each pair of relations as rel1\trel2
    public HashSet<String> disjointRelations = new HashSet<>();

    public boolean initialized = false;

    /****************************************************************
     * empty constructor for testing only
     */
    public Cache() {

    }

    /****************************************************************
     */
    public Cache(KB kb) {

        this.kb = kb;
    }

    /****************************************************************
     */
    public Cache(KBcache kbCacheIn, KB kbIn) {

        this.kb = kbIn;
        if (kbCacheIn.relations != null) {
            this.relations = Sets.newHashSet(kbCacheIn.relations);
        }
        if (kbCacheIn.transRels != null) {
            this.transRels = Sets.newHashSet(kbCacheIn.transRels);
        }
        if (kbCacheIn.instTransRels != null) {
            this.instTransRels = Sets.newHashSet(kbCacheIn.instTransRels);
        }
        if (kbCacheIn.parents != null) {
            for (Map.Entry<String, Map<String, Set<String>>> outerEntry : kbCacheIn.parents.entrySet()) {
                String outerKey = outerEntry.getKey();

                HashMap<String, HashSet<String>> newInnerMap = Maps.newHashMap();
                Map<String, Set<String>> oldInnerMap = outerEntry.getValue();
                for (Map.Entry<String, Set<String>> innerEntry : oldInnerMap.entrySet()) {
                    String innerKey = innerEntry.getKey();

                    HashSet newInnerSet = Sets.newHashSet(innerEntry.getValue());
                    newInnerMap.put(innerKey, newInnerSet);
                }
                this.parents.put(outerKey, newInnerMap);
            }
        }
        if (kbCacheIn.instanceOf != null) {
            for (Map.Entry<String, Set<String>> entry : kbCacheIn.instanceOf.entrySet()) {
                String key = entry.getKey();
                HashSet<String> newSet = Sets.newHashSet(entry.getValue());
                this.instanceOf.put(key, newSet);
            }
        }
        if (kbCacheIn.insts != null) {
            this.insts = Sets.newHashSet(kbCacheIn.insts);
        }
        if (kbCacheIn.children != null) {
            for (Map.Entry<String, Map<String, Set<String>>> outerEntry : kbCacheIn.children.entrySet()) {
                String outerKey = outerEntry.getKey();

                Map<String, Set<String>> newInnerMap = Maps.newHashMap();
                Map<String, Set<String>> oldInnerMap = outerEntry.getValue();
                for (Map.Entry<String, Set<String>> innerEntry : oldInnerMap.entrySet()) {
                    String innerKey = innerEntry.getKey();
                    if (innerEntry != null && innerEntry.getValue() != null) {
                        HashSet newInnerSet = Sets.newHashSet(innerEntry.getValue());
                        newInnerMap.put(innerKey, newInnerSet);
                    }
                }
                this.children.put(outerKey, newInnerMap);
            }
        }

        if (kbCacheIn.signatures != null) {
            for (Map.Entry<String, List<String>> entry : kbCacheIn.signatures.entrySet()) {
                String key = entry.getKey();
                ArrayList<String> newSet = Lists.newArrayList(entry.getValue());
                this.signatures.put(key, newSet);
            }
        }
        if (kbCacheIn.valences != null) {
            this.valences = Maps.newHashMap(kbCacheIn.valences);
        }
        if (kbCacheIn.explicitDisjoint != null) {
            for (Map.Entry<String, Set<String>> entry : kbCacheIn.explicitDisjoint.entrySet()) {
                String key = entry.getKey();
                HashSet<String> newSet = Sets.newHashSet(entry.getValue());
                this.explicitDisjoint.put(key, newSet);
            }
        }
    }

    /**************************************************************
     * An ArrayList utility method
     */
    public int getArity(String rel) {

        if (valences == null) {
            System.out.println("Error in KBcache.getArity(): null valences");
            return 0;
        }
        if (!valences.containsKey(rel)) {
            System.out.println("Error in KBcache.getArity(): " + rel + " not found");
            return 0;
        }
        return valences.get(rel);
    }

    /** ***************************************************************
     * An ArrayList utility method
     */
    private void arrayListReplace(ArrayList<String> al, int index, String newEl) {

        if (index > al.size()) {
            System.out.println("Error in KBcache.arrayListReplace(): index " + index +
                    " out of bounds.");
            return;
        }
        al.remove(index);
        al.add(index,newEl);
    }

    /** ***************************************************************
     * Find whether the given child has the given parent for the given
     * transitive relation.  Return false if they are equal
     */
    public boolean childOfP(String rel, String parent, String child) {

        if (debug) System.out.println("INFO in KBcache.childOfP(): relation, parent, child: "
                + rel + " " + parent + " " + child);
        if (parent.equals(child)) {
            return false;
        }
        Map<String,Set<String>> childMap = children.get(rel);
        if (childMap == null)
            return false;
        Set<String> childSet = childMap.get(parent);
        if (debug) System.out.println("INFO in KBcache.childOfP(): children of " + parent + " : " + childSet);
        if (childSet == null) {
            if (debug) System.out.println("INFO in KBcache.childOfP(): null childset for relation, parent, child: "
                    + rel + " " + parent + " " + child);
            return false;
        }
        if (debug) System.out.println("INFO in KBcache.childOfP(): child set contains " + child + " : " + childSet.contains(child));
        if (childSet.contains(child))
            return true;
        else
            return false;
    }

    /** *************************************************************
     * Returns true if i is an instance of c, else returns false.
     *
     * @param i A String denoting an instance.
     * @param c A String denoting a Class.
     * @return true or false.
     */
    public boolean isInstanceOf(String i, String c) {

        if (instanceOf.containsKey(i)) {
            HashSet<String> hashSet = instanceOf.get(i);
            if (hashSet == null) {
                System.out.println("Error in KBcache.isInstanceOf(): null result for " + i);
                return false;
            }
            if (hashSet.contains(c))
                return true;
            else
                return false;
        }
        else
            return false;
    }

    /** ***************************************************************
     * Find whether the given instance has the given parent class.
     * Include paths the have transitive relations between instances such
     * as an Attribute that is a subAttribute of another instance, which
     * in turn then is an instance of the given class.
     * Return false if they are equal.
     */
    public boolean transInstOf(String child, String parent) {

        HashSet<String> prents = instanceOf.get(child);
        if (prents != null)
            return prents.contains(parent);
        else
            return false;
    }

    /** ***************************************************************
     * Find whether the given class has the given parent class.
     */
    public boolean subclassOf(String child, String parent) {

        HashMap<String,HashSet<String>> prentsForRel = parents.get("subclass");
        if (prentsForRel != null) {
            HashSet<String> prents = prentsForRel.get(child);
            if (prents != null)
                return prents.contains(parent);
            else
                return false;
        }
        return false;
    }

    /** ***************************************************************
     * Find whether the given class is the subAttribute of the given parent class.
     */
    public boolean subAttributeOf(String child, String parent) {

        HashMap<String,HashSet<String>> prentsForRel = parents.get("subAttribute");
        if (prentsForRel != null) {
            HashSet<String> prents = prentsForRel.get(child);
            if (prents != null)
                return prents.contains(parent);
            else
                return false;
        }
        return false;
    }

    /** ***************************************************************
     */
    public void addInstance(String child, String parent) {

        HashSet<String> is = instances.get(parent);
        if (is == null) {
            is = new HashSet<>();
            instances.put(parent, is);
        }
        is.add(child);
    }

    /** ***************************************************************
     * Add a new instance from an existing one plus a suffix, updating the caches
     */
    public void extendInstance(String term, String suffix) {

        String sep = "__";
        //if (suffix.matches("\\d__.*"))  // variable arity has appended single underscore before arity
        //    sep = "_";
        String newTerm = term + sep + suffix;
        if (kb.terms.contains(newTerm))
            System.out.println("Warning in KBcache.extendInstance(): term already exists: " + newTerm);
        kb.terms.add(newTerm);
        HashSet<String> iset = instanceOf.get(term);
        instanceOf.put(newTerm,iset);
        //if (newTerm.endsWith("Fn"))
        //    System.out.println("KBcache.extendInstance(): instance parents of: " + newTerm + " are: " + iset);
        //System.out.println("extendInstance(): new term: " + newTerm + " parents: " + iset);
        relations.add(newTerm);

        // math and logic ops are not transitive
        //transRels = new HashSet<String>();
        // all the transitive relations between instances in the kb
        //instTransRels = new HashSet<String>();

        /** All the cached "parent" relations of all transitive relations
         * meaning the relations between all first arguments and the
         * transitive closure of second arguments.  The external HashMap
         * pairs relation name String keys to values that are the parent
         * relationships.  The interior HashMap is the set of terms and
         * their transitive closure of parents.
         */
        //parents = new HashMap<String, HashMap<String, HashSet<String>>>();

        // all the instances of a class key, including through subrelation
        // and subAttribute
        //instances = new HashMap<>();

        // logic, math op are not transitive so no need to update "children"

        /** Relation name keys and argument types with 0th arg always ""
         * except in the case of Functions where the 0th arg will be the
         * function range.
         * Note that types can be functions, rather than just terms. Note that
         * types (when there's a domainSubclass etc) are designated by a
         * '+' appended to the class name.
         **/
        ArrayList<String> sig = signatures.get(term);

        if (sig == null)
            System.out.println("Error in KBcache.extendInstance(): no sig for term " + term);
        List<String> newsig = SUMOtoTFAform.relationExtractSigFromName(newTerm);
        if (sig != null) {
            for (int i = 0; i < sig.size(); i++) {
                String orig = sig.get(i);
                if (i > newsig.size() - 1 || kb.isSubclass(orig, newsig.get(i)))
                    SUMOtoTFAform.safeSet((ArrayList) newsig, i, orig);
            }
        }
        signatures.put(newTerm,(ArrayList) newsig);

        // The number of arguments to each relation.  Variable arity is -1
        valences.put(newTerm,valences.get(term));
    }

    /** ***************************************************************
     * Record instances and their explicitly defined parent classes
     */
    public void buildDirectInstances() {

        List<Formula> forms = kb.ask("arg", 0, "instance");
        for (int i = 0; i < forms.size(); i++) {
            Formula f = forms.get(i);
            String child = f.getStringArgument(1);
            String parent = f.getStringArgument(2);
            addInstance(child,parent);
            HashMap<String,HashSet<String>> superclasses = parents.get("subclass");
            HashSet<String> iset = new HashSet<String>();
            if (instanceOf.get(child) != null)
                iset = instanceOf.get(child);
            iset.add(parent);
            if (superclasses != null && superclasses.get(parent) != null)
                iset.addAll(superclasses.get(parent));
            instanceOf.put(child, iset);
        }
    }

    /** ***************************************************************
     * Add transitive relationships to instances in the "instances" map
     */
    public void addTransitiveInstances() {

        for (String s : instances.keySet()) {
            HashSet<String> allInst = instances.get(s);
            allInst.addAll(getInstancesForType(s));
            instances.put(s,allInst);
        }
    }

    /** ***************************************************************
     */
    public void buildDisjointRelationsMap() {

        HashSet<String> pairs = new HashSet<>();
        ArrayList<Formula> explicitDisjointFormulae = new ArrayList<Formula>();
        explicitDisjointFormulae.addAll(kb.ask("arg", 0, "disjointRelation"));
        for (Formula f : explicitDisjointFormulae) {
            String arg1 = f.getStringArgument(1);
            String arg2 = f.getStringArgument(2);
            pairs.add(arg1 + "\t" + arg2);
            Set<String> children1 = getChildRelations(arg1);
            if (children1 == null)
                children1 = new HashSet<>();
            children1.add(arg1);
            Set<String> children2 = getChildRelations(arg2);
            if (children2 == null)
                children2 = new HashSet<>();
            children2.add(arg2);
            for (String c1 : children1) {
                for (String c2 : children2) {
                    if (!c1.equals(c2))
                        disjointRelations.add(c1 + "\t" + c2);
                }
            }
        }
    }

    /** ***************************************************************
     * build a disjoint-relations-map which were explicitly defined in
     * "partition", "exhaustiveDecomposition", "disjointDecomposition"
     * and "disjoint" expressions;
     */
    public void buildExplicitDisjointMap() {

        if (debug) System.out.println("buildExplicitDisjointMap()");
        ArrayList<Formula> explicitDisjointFormulae = new ArrayList<Formula>();
        explicitDisjointFormulae.addAll(kb.ask("arg", 0, "partition"));
        explicitDisjointFormulae.addAll(kb.ask("arg", 0, "disjoint"));
        explicitDisjointFormulae.addAll(kb.ask("arg", 0, "disjointDecomposition"));
        for (Formula f : explicitDisjointFormulae) {
            if (debug) System.out.println("buildExplicitDisjointMap(): check formula: " + f.getFormula());
            List<String> arguments = null;
            if (f.car().equals("disjoint"))
                arguments = f.argumentsToArrayListString(1);
            else
                arguments = f.argumentsToArrayListString(2);
            for (String key : arguments) {
                for (String val : arguments) {
                    if (key.equals(val))
                        continue;
                    if (!explicitDisjoint.containsKey(key)) {
                        HashSet<String> vals = new HashSet<>();
                        vals.add(val);
                        explicitDisjoint.put(key, vals);
                    }
                    else {
                        HashSet<String> vals = explicitDisjoint.get(key);
                        vals.add(val);
                        explicitDisjoint.put(key, vals);
                    }
                }
            }
        }
    }

    /** ***************************************************************
     * cache the transitive closure of disjoint relations
     */
    public void buildDisjointMap() {

        long t1 = System.currentTimeMillis();
        for (String p1 : explicitDisjoint.keySet()) {
            HashSet<String> vals = explicitDisjoint.get(p1);
            Set<String> children1 = getChildClasses(p1);
            if (children1 == null)
                children1 = new HashSet<>();
            children1.add(p1);
            for (String p2 : vals) {
                Set<String> children2 = getChildClasses(p2);
                if (children2 == null)
                    children2 = new HashSet<>();
                children2.add(p2);
                for (String c1 : children1) {
                    for (String c2 : children2) {
                        if (!c1.equals(c2))
                            disjoint.add(c1 + "\t" + c2);
                    }
                }
            }
        }
        System.out.println("buildDisjointMap():  " + ((System.currentTimeMillis() - t1) / 1000.0)
                + " seconds to process " + disjoint.size() + " entries");
    }

    /** ***************************************************************
     * check if there are any two types in typeSet are disjoint or not;
     */
    public boolean checkDisjoint(KB kb, HashSet<String> typeSet) {

        ArrayList<String> typeList = new ArrayList<>(typeSet);
        int size = typeList.size();
        for (int i = 0; i < size; i++) {
            String c1 = typeList.get(i);
            for (int j = i+1; j < size; j++) {
                String c2 = typeList.get(j);
                if (disjoint.contains(c1 + "\t" + c2) || disjoint.contains(c2 + "\t" + c1))
                    return true;
            }
        }
        return false;
    }

    /** ***************************************************************
     * check if rel1 and rel2 are disjoint
     * return true if rel1 and rel2 are disjoint; otherwise return false.
     */
    public boolean checkDisjoint(KB kb, String c1, String c2) {

        if (disjoint.contains(c1 + "\t" + c2) || disjoint.contains(c2 + "\t" + c1))
            return true;
        else
            return false;
        /**
         HashSet<String> ancestors_rel1 = kb.kbCache.getParentClasses(c1);
         HashSet<String> ancestors_rel2 = kb.kbCache.getParentClasses(c2);
         if (ancestors_rel1 == null || ancestors_rel2 == null)
         return false;

         ancestors_rel1.add(c1);
         ancestors_rel2.add(c2);
         for (String s1 : ancestors_rel1) {
         for (String s2 : ancestors_rel2) {
         if (kb.kbCache.isExplicitDisjoint(kb.kbCache.explicitDisjoint, s1, s2)) {
         if (debug)
         System.out.println(c1 + " and " + c2 +
         " are disjoint relations, because of " + s1 + " and " + s2);
         return true;
         }
         }
         }
         return false;
         **/
    }

    /** ***************************************************************
     * return true if rel1 and rel2 are explicitly defined as disjoint
     * relations; otherwise return false.
     */
    public boolean isExplicitDisjoint(HashMap<String, HashSet<String>> explicitDisjointRelations,
                                      String c1, String c2) {

        if (explicitDisjointRelations.containsKey(c1)) {
            return explicitDisjointRelations.get(c1).contains(c2);
        }
        else if (explicitDisjointRelations.containsKey(c2)) {
            return explicitDisjointRelations.get(c2).contains(c1);
        }
        else
            return false;
    }

    /** ***************************************************************
     * Cache whether a given instance has a given parent class.
     * Include paths the have transitive relations between instances such
     * as an Attribute that is a subAttribute of another instance, which
     * in turn then is an instance of the given class.
     * TODO: make sure that direct instances are recorded too
     */
    public void buildTransInstOf() {

        //System.out.println("buildTransInstOf(): contains Anger: " + insts.contains("Anger"));
        // Iterate through the temporary list of instances built during creation of the @see children map
        for (String child : insts) {
            List<Formula> forms = kb.ask("arg",1,child);
            if (debug) System.out.println("buildTransInstOf(): forms: " + forms);
            for (Formula f : forms) {
                String rel = f.getStringArgument(0);
                if (debug) System.out.println("buildTransInstOf(): rel: " + rel);
                if (instTransRels.contains(rel) && !rel.equals("subclass") && !rel.equals("relatedInternalConcept")) {
                    if (!intendedTransRels.contains(rel)) {
                        System.out.println("WARNING in buildTransInstOf(): using non-standard transitive relation " +
                                rel + " with child " + child + " .  May need to add to KBcache.intendedTransRels");
                    }
                    HashMap<String,HashSet<String>> prentList = parents.get(rel);
                    if (debug) System.out.println("buildTransInstOf(): prentList: " + prentList);
                    if (prentList != null) {
                        HashSet<String> prents = prentList.get(f.getStringArgument(1));  // include all parents of the child
                        if (debug) System.out.println("buildTransInstOf(): prents: " + prents);
                        if (prents != null) {
                            for (String p : prents) {
                                List<Formula> forms2 = kb.askWithRestriction(0,"instance",1,p);
                                if (debug) System.out.println("buildTransInstOf(): forms2: " + forms2);
                                for (Formula f2 : forms2) {
                                    String cl = f2.getStringArgument(2);
                                    if (debug) System.out.println("buildTransInstOf(): cl: " + cl);
                                    HashMap<String,HashSet<String>> superclasses = parents.get("subclass");
                                    HashSet<String> pset = new HashSet<String>();
                                    if (instanceOf.get(child) != null)
                                        pset = instanceOf.get(child);
                                    pset.add(cl);
                                    if (superclasses != null && superclasses.get(cl) != null)
                                        pset.addAll(superclasses.get(cl));
                                    instanceOf.put(child, pset);
                                }
                            }
                        }
                    }
                }
                else if (rel.equals("instance")) {
                    String cl = f.getStringArgument(2);
                    if (debug) System.out.println("buildTransInstOf(): cl2: " + cl);
                    HashMap<String,HashSet<String>> superclasses = parents.get("subclass");
                    HashSet<String> iset = new HashSet<String>();
                    if (instanceOf.get(child) != null)
                        iset = instanceOf.get(child);
                    iset.add(cl);
                    if (superclasses != null) {
                        HashSet<String> supers = superclasses.get(cl);
                        if (supers != null && supers.size() > 0)
                            iset.addAll(supers);
                    }
                    instanceOf.put(child, iset);
                }
            }
        }
        debug = false;
        buildDirectInstances();
    }

    /** ***************************************************************
     * since domains are collected before we know the instances of
     * VariableArityRelation we need to go back and correct valences
     */
    public void correctValences() {

        HashSet<String> hs = instances.get("VariableArityRelation");
        for (String s : hs) {
            valences.put(s,-1);
        }
    }

    /** ***************************************************************
     * @return the most specific parent of a set of classes
     */
    public String mostSpecificParent(HashSet<String> p1) {

        Map<String,Set<String>> subclasses = children.get("subclass");
        TreeSet<AVPair> countIndex = new TreeSet<AVPair>();
        Iterator<String> it = p1.iterator();
        while (it.hasNext()) {
            String cl = it.next();
            Set<String> classes = subclasses.get(cl);
            if (classes == null)
                System.out.println("Error in KBcache.mostSpecificParent(): no subclasses for : " + cl);
            else {
                int count = classes.size();
                String countString = Integer.toString(count);
                countString = StringUtil.fillString(countString, '0', 10, true);
                AVPair avp = new AVPair(countString, cl);
                countIndex.add(avp);
            }
        }
        return countIndex.first().value;
    }

    /** ***************************************************************
     * @return the most specific parent of the two parameters or null if
     * there is no common parent.  TODO: Take into
     * account that there are instances, classes, relations, and attributes,
     */
    public String getCommonParent(String t1, String t2) {

        HashSet<String> p1 = new HashSet<>();
        HashSet<String> p2 = new HashSet<>();
        if (kb.isInstance(t1)) {
            HashSet<String> temp = getParentClassesOfInstance(t1);
            if (temp != null)
                p1.addAll(temp);
        }
        else {
            HashSet<String> temp = getParentClasses(t1);
            if (temp != null)
                p1.addAll(temp);
        }
        if (kb.isInstance(t2)) {
            HashSet<String> temp = getParentClassesOfInstance(t2);
            if (temp != null)
                p2.addAll(temp);
        }
        else {
            HashSet<String> temp = getParentClasses(t2);
            if (temp != null)
                p2.addAll(temp);
        }
        p1.retainAll(p2);
        if (p1.isEmpty())
            return null;
        if (p1.size() == 1)
            return p1.iterator().next();

        return mostSpecificParent(p1);
    }

    /** ***************************************************************
     */
    public String getCommonChild(HashSet<String> t2) {

        String common = "Entity";
        System.out.println("types " + t2);
        for (String c1 : t2) {
            if (debug) System.out.println("term depth " + c1 + " : " + kb.termDepth(c1));
            if (debug) System.out.println("term depth " + common + " : " + kb.termDepth(common));
            if (kb.compareTermDepth(c1,common) > 0)
                common = c1;
        }
        return common;
    }

    /** ***************************************************************
     * return parent classes for the given cl from subclass expressions.
     */
    public HashSet<String> getParentClasses(String cl) {

        HashMap<String,HashSet<String>> ps = parents.get("subclass");
        if (ps != null)
            return ps.get(cl);
        else
            return null;
    }

    /** ***************************************************************
     * return child relations for the given rel from subrelation expressions.
     */
    public Set<String> getChildRelations(String rel) {

        Map<String,Set<String>> ps = children.get("subrelation");
        if (ps != null)
            return ps.get(rel);
        else
            return null;
    }

    /** ***************************************************************
     * return child classes for the given cl from subclass expressions.
     */
    public Set<String> getChildClasses(String cl) {

        Map<String,Set<String>> ps = children.get("subclass");
        if (ps != null)
            return ps.get(cl);
        else
            return null;
    }

    /** ***************************************************************
     * return child term for the given cl from rel expressions.
     */
    public Set<String> getChildTerms(String cl, String rel) {

        Map<String,Set<String>> ps = children.get(rel);
        if (ps != null)
            return ps.get(cl);
        else
            return null;
    }

    /** ***************************************************************
     * return child classes for the given cl from subclass expressions.
     */
    public HashSet<String> getChildInstances(String cl) {

        HashSet<String> result = new HashSet<>();
        Map<String,Set<String>> ps = children.get("subclass");
        if (ps != null && ps.values() != null) {
            for (String cc : ps.get(cl)) {
                HashSet<String> insts = getInstancesForType(cc);
                if (insts != null)
                    result.addAll(insts);
            }
            return result;
        }
        else
            return null;
    }

    /** ***************************************************************
     * return classes for the given instance cl.
     *
     * For example, if we know (instance UnitedStates Nation), then
     * getParentClassesOfInstances(UnitedStates) returns Nation and its
     * super classes from subclass expressions.
     */
    public HashSet<String> getParentClassesOfInstance(String cl) {

        HashSet<String> ps = instanceOf.get(cl);
        if (ps != null)
            return ps;
        else
            return new HashSet<String>();
    }

    /** ***************************************************************
     * Get all instances for the given input class
     *
     * For example, given the class "Nation", getInstancesForType(Nation)
     * returns all instances, like "America", "Austria", "Albania", etc.
     *
     * Follow instances through transitive relations if applicable from
     * the set of [subAttribute, subrelation].
     *
     * TODO: do we need a DownwardHeritableRelation so that this
     * list doesn't need to be hardcoded?
     */
    public HashSet<String> getInstancesForType(String cl) {

        if (debug) System.out.println("getInstancesForType(): " + cl);
        Set<String> instancesForType = new HashSet<>();
        Map<String,Set<String>> ps = children.get("subclass");
        Set<String> classes = new HashSet<>();
        if (ps != null)
            classes = ps.get(cl);
        if (debug) System.out.println("getInstancesForType(): subclasses of " + cl + " : " + classes);
        if (classes == null)
            classes = new HashSet<>();
        classes.add(cl);
        for (String c : classes) {
            HashSet<String> is = instances.get(c);
            if (debug) System.out.println("getInstancesForType(): instances of " + c + " : " + is);
            if (is != null)
                instancesForType.addAll(is);
        }
        if (debug) System.out.println("getInstancesForType(): " + instancesForType);
        HashSet<String> instancesForType2 = new HashSet<>();
        Map<String,Set<String>> attr = children.get("subAttribute");
        Map<String,Set<String>> arel = children.get("subrelation");
        for (String i : instancesForType) {
            Set<String> temp = null;
            if (attr != null) {
                temp = attr.get(i);
                if (temp != null)
                    instancesForType2.addAll(temp);
            }
            if (arel != null) {
                temp = arel.get(i);
                if (temp != null)
                    instancesForType2.addAll(temp);
            }
        }
        instancesForType2.addAll(instancesForType);
        if (debug) System.out.println("getInstancesForType(): 2: " + instancesForType2);
        return instancesForType2;
    }

    /** ***************************************************************
     */
    public ArrayList<String> getSignature(String rel) {

        return signatures.get(rel);
    }

    /** ***************************************************************
     * Get the range (return type) of a Function.
     * @return null if argument is not a function
     */
    public String getRange(String f) {

        if (!kb.isFunction(f))
            return null;
        ArrayList<String> sig = getSignature(f);
        if (sig == null || sig.size() == 0)
            return null;
        return sig.get(0);
    }

    /** ***************************************************************
     * Get the HashSet of the given arguments from an ArrayList of Formulas.
     */
    public static Set<String> collectArgFromFormulas(int arg, List<Formula> forms) {

        Set<String> subs = new HashSet<String>();
        for (Formula f : forms) {
            String sub = f.getStringArgument(arg);
            //System.out.println("collectArgFromFormulas(): " + f + "\n" + arg + "\n" + sub);
            subs.add(sub);
        }
        //System.out.println("collectArgFromFormulas(): subs: " + subs);
        return subs;
    }

    /** ***************************************************************
     * Do a proper search for relations (including Functions), utilizing
     * the formal definitions, rather than the convention of initial
     * lower case letter.  This means getting any instance of Relation
     * tracing back through subclasses as well.
     */
    public void buildTransitiveRelationsSet() {

        if (debug) System.out.println("INFO in KBcache.buildTransitiveRelationsSet(): begin");
        HashSet<String> rels = new HashSet<String>();
        rels.add("TransitiveRelation");
        while (!rels.isEmpty()) {
            Set<String> relSubs = new HashSet<>();
            for (String rel : rels) {
                relSubs = new HashSet<>();
                List<Formula> forms = kb.askWithRestriction(0, "subclass", 2, rel);
                if (forms != null && forms.size() != 0) {
                    if (debug) System.out.println("INFO in KBcache.buildTransitiveRelationsSet(): subclasses: " + forms);
                    relSubs.addAll(collectArgFromFormulas(1,forms));
                }
                else
                if (debug) System.out.println("INFO in KBcache.buildTransitiveRelationsSet(): no subclasses for : " + rels);
                forms = kb.askWithRestriction(0,"instance",2,rel);
                if (forms != null && forms.size() != 0)
                    transRels.addAll(collectArgFromFormulas(1,forms));
                forms = kb.askWithRestriction(0,"subrelation",2,rel);
                if (forms != null && forms.size() != 0)
                    transRels.addAll(collectArgFromFormulas(1,forms));
            }
            rels = new HashSet<String>();
            rels.addAll(relSubs);
        }
    }

    /** ***************************************************************
     * Do a proper search for relations (including Functions), utilizing
     * the formal definitions, rather than the convention of initial
     * lower case letter.  This means getting any instance of Relation
     * tracing back through subclasses as well.
     */
    public void buildRelationsSet() {

        HashSet<String> rels = new HashSet<String>();
        rels.add("Relation");
        while (!rels.isEmpty()) {
            //System.out.println("INFO in KBcache.buildRelationsSet(): rels: " + rels);
            HashSet<String> relSubs = new HashSet<String>();
            Iterator<String> it = rels.iterator();
            while (it.hasNext()) {
                String rel = it.next();
                //System.out.println("INFO in KBcache.buildRelationsSet(): rel: " + rel);
                List<Formula> forms = kb.askWithRestriction(0,"subclass",2,rel);
                //System.out.println("INFO in KBcache.buildRelationsSet(): forms1: " + forms);
                if (forms != null)
                    relSubs.addAll(collectArgFromFormulas(1,forms));
                forms = kb.askWithRestriction(0,"instance",2,rel);
                //System.out.println("INFO in KBcache.buildRelationsSet(): forms2: " + forms);
                if (forms != null) {
                    relations.addAll(collectArgFromFormulas(1,forms));
                    relSubs.addAll(collectArgFromFormulas(1,forms));
                }
                forms = kb.askWithRestriction(0,"subrelation",2,rel);
                //System.out.println("INFO in KBcache.buildRelationsSet(): forms3: " + forms);
                if (forms != null) {
                    relations.addAll(collectArgFromFormulas(1,forms));
                    relSubs.addAll(collectArgFromFormulas(1,forms));
                }
            }
            //System.out.println("INFO in KBcache.buildRelationsSet(): relSubs: " + relSubs);
            rels = new HashSet<String>();
            rels.addAll(relSubs);
        }
    }

    /** ***************************************************************
     */
    public void buildFunctionsSet() {

        for (String s : relations)
            if (isInstanceOf(s,"Function"))  // can't use isFunction since that checks KBcache.functions
                functions.add(s);
            else
                predicates.add(s);
    }

    /** ***************************************************************
     * Find the parent "roots" of any transitive relation - terms that
     * appear only as argument 2
     */
    private HashSet<String> findRoots(String rel) {

        HashSet<String> result = new HashSet<String>();
        List<Formula> forms = kb.ask("arg",0,rel);
        Set<String> arg1s = collectArgFromFormulas(1,forms);
        Set<String> arg2s = collectArgFromFormulas(2,forms);
        arg2s.removeAll(arg1s);
        result.addAll(arg2s);
        //System.out.println("findRoots(): rel, roots: " + rel + ":" + result);
        return result;
    }

    /** ***************************************************************
     * Find the child "roots" of any transitive relation - terms that
     * appear only as argument 1
     */
    private HashSet<String> findLeaves(String rel) {

        HashSet<String> result = new HashSet<String>();
        List<Formula> forms = kb.ask("arg",0,rel);
        Set<String> arg1s = collectArgFromFormulas(1,forms);
        Set<String> arg2s = collectArgFromFormulas(2,forms);
        arg1s.removeAll(arg2s);
        result.addAll(arg1s);
        return result;
    }

    /** ***************************************************************
     * Build "parent" relations based on breadth first search algorithm.
     */
    private void breadthFirstBuildParents(String root, String rel) {

        HashMap<String,HashSet<String>> relParents = parents.get(rel);
        if (relParents == null) {
            System.out.println("Error in KBcache.breadthFirstBuildParents(): no relation " + rel);
            return;
        }
        int threshold = 10;      // maximum time that a term can be traversed in breadthFirstBuildParents()
        HashMap<String, Integer> appearanceCount = new HashMap<>();  // for each term, we count how many times it has been traversed
        ArrayDeque<String> Q = new ArrayDeque<String>();
        Q.add(root);
        while (!Q.isEmpty()) {
            String t = Q.remove();
            //System.out.println("visiting " + t);
            List<Formula> forms = kb.askWithRestriction(0,rel,2,t);
            if (forms != null) {
                Set<String> relSubs = collectArgFromFormulas(1,forms);

                Iterator<String> it = relSubs.iterator();
                while (it.hasNext()) {
                    String newTerm = it.next();
                    HashSet<String> newParents = new HashSet<>();
                    HashSet<String> oldParents = relParents.get(t);
                    if (oldParents == null) {
                        oldParents = new HashSet<>();
                        relParents.put(t, oldParents);
                    }
                    newParents.addAll(oldParents);
                    newParents.add(t);
                    HashSet<String> newTermParents = relParents.get(newTerm);
                    if (newTermParents != null)
                        newParents.addAll(newTermParents);
                    relParents.put(newTerm, newParents);

                    if (appearanceCount.get(newTerm) == null) {
                        appearanceCount.put(newTerm, 1);
                        Q.addFirst(newTerm);
                    }
                    else if (appearanceCount.get(newTerm) <= threshold) {
                        appearanceCount.put(newTerm, appearanceCount.get(newTerm)+1);
                        Q.addFirst(newTerm);
                    }
                }
            }
        }
    }

    /** ***************************************************************
     * Build "children" relations based on breadth first search algorithm.
     * Note that this routine expects to build "up" from the leaves.
     */
    private void breadthFirstBuildChildren(String leaf, String rel) {

        Map<String,Set<String>> relChildren = children.get(rel);
        if (relChildren == null) {
            System.out.println("Error in KBcache.breadthFirstBuildChildren(): no relation " + rel);
            return;
        }
        //if (debug) System.out.println("INFO in KBcache.breadthFirstBuildChildren(): trying relation " + rel);
        ArrayDeque<String> Q = new ArrayDeque<>();
        HashSet<String> V = new HashSet<>();
        Q.add(leaf);
        V.add(leaf);
        while (!Q.isEmpty()) {
            String child = Q.remove();
            //if (debug) System.out.println("visiting " + child);
            List<Formula> forms = kb.askWithRestriction(0,rel,1,child);
            if (debug) System.out.println("forms " + forms);
            if (forms != null) {
                Set<String> relParents = collectArgFromFormulas(2,forms);
                //if (debug) System.out.println("visiting direct parents of " + child +  ": " + relParents);
                for (String newTerm : relParents) {
                    //if (debug && newTerm.indexOf("RealNumber") > -1)
                    //    System.out.println("visiting parent  " + newTerm);
                    Set<String> newChildren = new HashSet<>();
                    Set<String> oldChildren = relChildren.get(child);
                    //if (debug) System.out.println("existing children of " + child +  ": " + oldChildren);
                    if (oldChildren == null) {
                        oldChildren = new HashSet<>();
                        relChildren.put(child, oldChildren);
                    }
                    newChildren.addAll(oldChildren);
                    newChildren.add(child);
                    Set<String> newTermChildren = relChildren.get(newTerm);
                    if (newTermChildren != null)
                        newChildren.addAll(newTermChildren);
                    relChildren.put(newTerm, newChildren);
                    //if (debug && newTerm.indexOf("RealNumber") > -1)
                    //    System.out.println("new children of  " + newTerm +  ": " + newChildren);
                    if (!V.contains(newTerm)) { // this is a DAG, not a tree, so we may have to visit nodes more than once
                        V.add(newTerm);
                        Q.addFirst(newTerm);
                    }
                }
            }
        }
        insts.addAll(relChildren.keySet());
    }

    /** ***************************************************************
     */
    private HashSet<String> visited = new HashSet<>();

    /** ***************************************************************
     * Build "children" relations recursively from the root
     */
    private Set<String> buildChildrenNew(String term, String rel) {

        if (debug) System.out.println("buildChildrenNew(): looking at " + term + " with relation " + rel);
        if (children.get(rel) == null)
            children.put(rel,new HashMap<>());
        Map<String,Set<String>> allChildren = children.get(rel);
        if (visited.contains(term))
            return allChildren.get(term);
        visited.add(term);
        if (debug) System.out.println("buildChildrenNew(): " + kb.ask("arg",0,"subrelation"));
        List<Formula> forms = kb.askWithRestriction(0,rel,2,term); // argument 2 is the "parent" in any binary relation
        if (debug) System.out.println("buildChildrenNew(): forms  " + forms);
        if (forms == null || forms.size() == 0) {
            return new HashSet<>();
        }
        Set<String> collectedChildren = new HashSet<>();
        for (Formula f : forms) {
            if (f.isCached() || StringUtil.emptyString(f.sourceFile))
                continue;
            //System.out.println(f.sourceFile);
            String newTerm = f.getStringArgument(1);// argument 1 is the "child" in any binary relation
            if (debug) System.out.println("buildChildrenNew(): new term " + newTerm);
            Set<String> children = buildChildrenNew(newTerm, rel);
            if (debug) System.out.println("buildChildrenNew(): children of " + newTerm + " are " + children);
            if (allChildren.containsKey(newTerm) && allChildren.get(newTerm) != null)
                children.addAll(allChildren.get(newTerm));
            allChildren.put(newTerm, children);
            if (children != null)
                collectedChildren.addAll(children);
            collectedChildren.add(newTerm);
        }
        //collectedChildren.add(term);
        if (debug) System.out.println("buildChildrenNew(): return  " + term + " with " + collectedChildren);
        if (debug) System.out.println();
        return collectedChildren;
    }

    /** ***************************************************************
     * Find all instances
     */
    public void buildInsts() {

        HashSet<String> rels = new HashSet<>();
        rels.add("instance");
        rels.add("subAttribute");
        rels.add("subField");
        for (String r : rels) {
            //System.out.println("buildInsts(): rel:  " + r);
            List<Formula> forms = kb.ask("arg",0,r);
            for (Formula f : forms) {
                //System.out.println("buildInsts(): form:  " + f);
                String arg = f.getStringArgument(1);
                insts.add(arg);
            }
        }
    }

    /** ***************************************************************
     * For each transitive relation, find its transitive closure.  If
     * rel is transitive, and (rel A B) and (rel B C) then the entry for
     * rel is a HashMap where the key A has value ArrayList of {B,C}.
     */
    public void buildParents() {

        if (debug) System.out.println("INFO in KBcache.buildParents():");
        Iterator<String> it = transRels.iterator();
        for (String rel : transRels) {
            HashMap<String,HashSet<String>> value = new HashMap<String,HashSet<String>>();
            HashSet<String> roots = findRoots(rel);
            if (debug) System.out.println("INFO in KBcache.buildParents(): roots for rel: " +
                    rel + "\n" + roots);
            parents.put(rel, value);
            Iterator<String> it1 = roots.iterator();
            while (it1.hasNext()) {
                String root = it1.next();
                breadthFirstBuildParents(root,rel);
            }
        }
    }

    /** ***************************************************************
     * For each transitive relation, find its transitive closure.  If
     * rel is transitive, and (rel A B) and (rel B C) then the entry for
     * rel is a HashMap where the key A has value ArrayList of {B,C}. Note
     * that this routine builds "up" from the leaves
     */
    public void buildChildren() {

        if (debug) System.out.println("INFO in KBcache.buildChildren()");
        for (String rel : transRels) {
            if (debug) System.out.println("INFO in KBcache.buildChildren(): rel: " + rel);
            Map<String,Set<String>> value = new HashMap<>();
            HashSet<String> roots = findRoots(rel);
            if (debug) System.out.println("INFO in KBcache.buildChildren(): roots: " + roots);
            children.put(rel, value);
            for (String root : roots) {
                visited = new HashSet<>(); // reset the visited list for each new root and relation
                Set<String> c = buildChildrenNew(root, rel);
                if (c != null)
                    value.put(root,c);
                insts.add(root);// TODO: shouldn't need this
            }
        }
    }

    /** ***************************************************************
     * Fill an array of String with the specified String up to but
     * not including the index, starting from the 1st argument and
     * ignoring the 0th argument.
     */
    private static void fillArray(String st, String[] ar, int start, int end) {

        for (int i = start; i < end; i++)
            if (StringUtil.emptyString(ar[i]))
                ar[i] = st;
    }

    /** ***************************************************************
     * Fill an array of String with the specified String up to but
     * not including the index, starting from the end of the array
     */
    private static void fillArrayList(String st, ArrayList<String> ar, int start, int end) {

        for (int i = start; i < end; i++)
            if (i > ar.size()-1 || StringUtil.emptyString(ar.get(i)))
                ar.add(st);
    }

    /** ***************************************************************
     * Build the argument type list for every relation. If the argument
     * is a domain subclass, append a "+" to the argument type.  If
     * no domain is defined for the given relation and argument position,
     * inherit it from the parent.  If there is no argument type, send
     * an error to the Sigma error list.
     * Relation name keys and argument types with 0th arg always "" except
     *   for functions which will have the range type as their 0th argument
     * public HashMap<String,ArrayList<String>> signatures =
     *      new HashMap<String,ArrayList<String>>();
     */
    public void collectDomains() {

        Iterator<String> it = relations.iterator();
        while (it.hasNext()) {
            String rel = it.next();
            String[] domainArray = new String[Formula.MAX_PREDICATE_ARITY];
            int maxIndex = 0;
            domainArray[0] = "";
            List<Formula> forms = kb.askWithRestriction(0,"domain",1,rel);
            if (forms != null) {
                for (int i = 0; i < forms.size(); i++) {
                    Formula form = forms.get(i);
                    if (debug) System.out.println("INFO in KBcache.collectDomains(): form " + form);
                    String arg2 = form.getStringArgument(2);
                    if (StringUtil.emptyString(arg2) || !StringUtil.isNumeric(arg2)) {
                        System.out.println("Error in KBcache.collectDomains(): arg2 not a number in:  " + form);
                        continue;
                    }
                    int arg = Integer.parseInt(form.getStringArgument(2));
                    String type = form.getStringArgument(3);
                    domainArray[arg] = type;
                    if (arg > maxIndex)
                        maxIndex = arg;
                }
            }

            forms = kb.askWithRestriction(0,"domainSubclass",1,rel);
            if (forms != null) {
                for (int i = 0; i < forms.size(); i++) {
                    Formula form = forms.get(i);
                    int arg = Integer.parseInt(form.getStringArgument(2));
                    String type = form.getStringArgument(3);
                    domainArray[arg] = type + "+";
                    if (arg > maxIndex)
                        maxIndex = arg;
                }
            }

            forms = kb.askWithRestriction(0,"range",1,rel);
            if (forms != null) {
                if (forms.size() > 1)
                    System.out.println("Warning in KBcache.collectDomains(): more than one range statement" + forms);
                for (int i = 0; i < forms.size(); i++) {
                    Formula form = forms.get(i);
                    String type = form.getStringArgument(2);
                    domainArray[0] = type;
                }
            }

            forms = kb.askWithRestriction(0,"rangeSubclass",1,rel);
            if (forms != null) {
                if (forms.size() > 1)
                    System.out.println("Warning in KBcache.collectDomains(): more than one rangeSubclass statement" + forms);
                for (int i = 0; i < forms.size(); i++) {
                    Formula form = forms.get(i);
                    String type = form.getStringArgument(2);
                    domainArray[0] = type + "+";
                }
            }

            fillArray("Entity",domainArray,1,maxIndex); // set default arg type of Entity in case user forgets
            ArrayList<String> domains = new ArrayList<String>();
            for (int i = 0; i <= maxIndex; i++)
                domains.add(domainArray[i]);
            if (debug) System.out.println("INFO in KBcache.collectDomains(): rel: " + rel);
            if (debug) System.out.println("INFO in KBcache.collectDomains(): domains: " + domains);
            signatures.put(rel,domains);
            valences.put(rel, Integer.valueOf(maxIndex));
        }
        inheritDomains();
    }

    /** ***************************************************************
     * Note that this routine forces child relations to have arguments
     * that are the same or more specific than their parent relations.
     */
    private void breadthFirstInheritDomains(String root) {

        String rel = "subrelation";
        HashMap<String,HashSet<String>> relParents = parents.get("subrelation");
        if (relParents == null) {
            System.out.println("Error in KBcache.breadthFirstInheritDomains(): no relation subrelation");
            return;
        }
        ArrayDeque<String> Q = new ArrayDeque<>();
        HashSet<String> V = new HashSet<>();
        Q.add(root);
        V.add(root);
        while (!Q.isEmpty()) {
            String t = Q.remove();
            ArrayList<String> tdomains = signatures.get(t);
            List<Formula> forms = kb.askWithRestriction(0,rel,2,t);
            if (forms != null) {
                Set<String> relSubs = collectArgFromFormulas(1,forms);
                Iterator<String> it = relSubs.iterator();
                while (it.hasNext()) {
                    String newTerm = it.next();
                    ArrayList<String> newDomains = signatures.get(newTerm);
                    if (valences.get(t) == null) {
                        System.out.println("Error in KBcache.breadthFirstInheritDomains(): no valence for " + t);
                        continue;
                    }
                    else if (valences.get(newTerm) == null || valences.get(newTerm) < valences.get(t)) {
                        fillArrayList("Entity",newDomains,valences.get(newTerm)+1,valences.get(t)+1);
                        valences.put(newTerm, valences.get(t));
                    }
                    for (int i = 1; i < valences.get(t); i++) {
                        String childArgType = newDomains.get(i);
                        String parentArgType = tdomains.get(i);
                        // If child-relation does not have definition of argument-type, we use parent-relation's argument-type
                        // TODO: if parent-relation does not have definition of argument-type, we continue to find its parent until we find the definition of argument-type
                        if (kb.askWithTwoRestrictions(0, "domain", 1, newTerm, 3, childArgType).isEmpty()) {
                            arrayListReplace(newDomains,i,parentArgType);
                        }
                    }
                    if (!V.contains(newTerm)) {
                        V.add(newTerm);
                        Q.addFirst(newTerm);
                    }
                }
            }
        }
    }

    /** *************************************************************
     * Delete and writes the cache .kif file then call addConstituent() so
     * that the file can be processed and loaded by the inference engine.
     */
    public void writeCacheFile() {

        long millis = System.currentTimeMillis();
        FileWriter fw = null;
        try {
            File dir = new File(KBmanager.getMgr().getPref("kbDir"));
            File f = new File(dir, (kb.name + _cacheFileSuffix));
            System.out.println("INFO in KBcache.writeCacheFile(): " + f.getName());
            if (f.exists())
                f.delete();
            String filename = f.getCanonicalPath();
            fw = new FileWriter(f, true);
            Iterator<String> it = parents.keySet().iterator();
            while (it.hasNext()) {
                String rel = it.next();
                HashMap<String,HashSet<String>> valSet = parents.get(rel);
                Iterator<String> it2 = valSet.keySet().iterator();
                while (it2.hasNext()) {
                    String child = it2.next();
                    HashSet<String> prents = valSet.get(child);
                    Iterator<String> it3 = prents.iterator();
                    while (it3.hasNext()) {
                        String parent = it3.next();
                        String tuple = "(" + rel + " " + child + " " + parent + ")";
                        if (!kb.formulaMap.containsKey(tuple)) {
                            fw.write(tuple);
                            fw.write(System.getProperty("line.separator"));
                        }
                    }
                }
            }

            it = instanceOf.keySet().iterator();
            while (it.hasNext()) {
                String inst = it.next();
                HashSet<String> valSet = instanceOf.get(inst);
                Iterator<String> it2 = valSet.iterator();
                while (it2.hasNext()) {
                    String parent = it2.next();
                    String tuple = "(instance " + inst + " " + parent + ")";
                    if (!kb.formulaMap.containsKey(tuple)) {
                        fw.write(tuple);
                        fw.write(System.getProperty("line.separator"));
                    }
                }
            }
            if (fw != null) {
                fw.close();
                fw = null;
            }
            System.out.println("KBcache.writeCacheFile(): done writing cache file, in seconds: " + (System.currentTimeMillis() - millis) / 1000);
            millis = System.currentTimeMillis();
            kb.constituents.remove(filename);
            kb.addConstituent(filename);
            System.out.println("KBcache.writeCacheFile(): add cache file, in seconds: " + (System.currentTimeMillis() - millis) / 1000);
            //kb.addConstituent(filename, false, false, false);
            //KBmanager.getMgr().writeConfiguration();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (fw != null)
                    fw.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /** ***************************************************************
     * Find domain and domainSubclass definitions that impact a child
     * relation.  If the type of an argument is less specific than
     * the same type of a parent's argument, use that of the parent.
     */
    public void inheritDomains() {

        HashSet<String> roots = findRoots("subrelation");
        Iterator<String> it = roots.iterator();
        while (it.hasNext()) {
            String root = it.next();
            breadthFirstInheritDomains(root);
        }
    }

    /** ***************************************************************
     * Compile the set of transitive relations that are between instances
     */
    public void buildInstTransRels() {

        Iterator<String> it = transRels.iterator();
        while (it.hasNext()) {
            String rel = it.next();
            //System.out.println("KBcache.buildInstTransRels(): -------------------: " + rel);
            boolean instrel = true;
            ArrayList<String> sig = signatures.get(rel);
            if (sig == null) {
                System.out.println("Error in KBcache.buildInstTransRels(): Error " + rel + " not found.");
            }
            else {
                for (int i = 0; i < sig.size(); i++) {
                    String signatureElement = sig.get(i);
                    //System.out.println("KBcache.buildInstTransRels(): " + signatureElement);
                    if (signatureElement.endsWith("+") || signatureElement.equals("SetOrClass")) {
                        //System.out.println("KBcache.buildInstTransRels(): " + rel + " is between classes");
                        instrel = false;
                        break;
                    }
                }
                if (instrel)
                    instTransRels.add(rel);
            }
        }
    }

    /** ***************************************************************
     * Main entry point for the class.
     */
    public void buildCaches() {

        long millis = System.currentTimeMillis();
        if (debug) System.out.println("INFO in KBcache.buildCaches()");
        buildInsts();
        System.out.println("KBcache.buildCaches(): buildInsts seconds: " + (System.currentTimeMillis() - millis) / 1000);
        millis = System.currentTimeMillis();
        buildRelationsSet();
        System.out.println("KBcache.buildCaches(): buildRelationsSet seconds: " + (System.currentTimeMillis() - millis) / 1000);
        millis = System.currentTimeMillis();
        buildTransitiveRelationsSet();
        System.out.println("KBcache.buildCaches(): buildTransitiveRelationsSet seconds: " + (System.currentTimeMillis() - millis) / 1000);
        millis = System.currentTimeMillis();
        buildParents();
        System.out.println("KBcache.buildCaches(): buildParents seconds: " + (System.currentTimeMillis() - millis) / 1000);
        millis = System.currentTimeMillis();
        buildChildren(); // note that buildTransInstOf() depends on this
        System.out.println("KBcache.buildCaches(): buildChildren seconds: " + (System.currentTimeMillis() - millis) / 1000);
        millis = System.currentTimeMillis();
        collectDomains();  // note that buildInstTransRels() depends on this
        System.out.println("KBcache.buildCaches(): collectDomains seconds: " + (System.currentTimeMillis() - millis) / 1000);
        millis = System.currentTimeMillis();
        buildInstTransRels();
        System.out.println("KBcache.buildCaches(): buildInstTransRels seconds: " + (System.currentTimeMillis() - millis) / 1000);
        millis = System.currentTimeMillis();
        buildDirectInstances();
        System.out.println("KBcache.buildCaches(): buildDirectInstances seconds: " + (System.currentTimeMillis() - millis) / 1000);
        millis = System.currentTimeMillis();
        addTransitiveInstances();
        System.out.println("KBcache.buildCaches(): addTransitiveInstances seconds: " + (System.currentTimeMillis() - millis) / 1000);
        millis = System.currentTimeMillis();
        buildTransInstOf();
        correctValences(); // correct VariableArityRelation valences
        System.out.println("KBcache.buildCaches(): buildTransInstOf seconds: " + (System.currentTimeMillis() - millis) / 1000);
        millis = System.currentTimeMillis();
        buildExplicitDisjointMap(); // find relations under partition definition
        System.out.println("KBcache.buildCaches(): buildExplicitDisjointMap seconds: " + (System.currentTimeMillis() - millis) / 1000);
        millis = System.currentTimeMillis();
        buildDisjointMap();
        System.out.println("KBcache.buildCaches(): buildDisjointMap seconds: " + (System.currentTimeMillis() - millis) / 1000);
        millis = System.currentTimeMillis();
        buildFunctionsSet();
        System.out.println("KBcache.buildCaches(): buildFunctionsSet seconds: " + (System.currentTimeMillis() - millis) / 1000);
        millis = System.currentTimeMillis();
        writeCacheFile();
        System.out.println("KBcache.buildCaches(): writeCacheFile seconds: " + (System.currentTimeMillis() - millis) / 1000);
        millis = System.currentTimeMillis();
        System.out.println("INFO in KBcache.buildCaches(): size: " + instanceOf.keySet().size());
        initialized = true;
    }

    /** ***************************************************************
     * Copy all relevant information from a VariableArityRelation to a new
     * predicate that is a particular fixed arity. Fill the signature from
     * final argument type in the predicate
     */
    public void copyNewPredFromVariableArity(String pred, String oldPred, int arity) {

        ArrayList<String> oldSig = signatures.get(oldPred);
        ArrayList<String> newSig = new ArrayList(oldSig);
        if (signatures.keySet().contains(oldPred))
            signatures.put(pred,newSig);
        String lastType = oldSig.get(oldSig.size()-1);
        for (int i = oldSig.size(); i <= arity; i++) {
            newSig.add(lastType);
        }
        if (instanceOf.keySet().contains(oldPred))
            instanceOf.put(pred, instanceOf.get(oldPred));
        valences.put(pred,arity);
    }

    /** ***************************************************************
     * @return the type of the last argument to the given relation,
     * which will be the type of all the expanded row variables
     */
    public String variableArityType(String r) {

        ArrayList<String> sig = getSignature(r);
        if (sig == null)
            System.out.println("Error in variableArityType() null signature for " + r);
        String type = sig.get(sig.size() - 1);
        return type;
    }

    /** *************************************************************
     */
    public void showState() {

        System.out.println("-------------- relations ----------------");
        Iterator<String> it = this.relations.iterator();
        while (it.hasNext())
            System.out.print(it.next() + " ");
        System.out.println();
        //nkbc.buildTransitiveRelationsSet();
        System.out.println("-------------- transitives ----------------");
        it = this.transRels.iterator();
        while (it.hasNext())
            System.out.print(it.next() + " ");
        System.out.println();
        System.out.println("-------------- parents ----------------");
        //nkbc.buildParents();
        it = this.parents.keySet().iterator();
        while (it.hasNext()) {
            String rel = it.next();
            System.out.println("Relation: " + rel);
            HashMap<String,HashSet<String>> relmap = this.parents.get(rel);
            Iterator<String> it2 = relmap.keySet().iterator();
            while (it2.hasNext()) {
                String term = it2.next();
                System.out.println(term + ": " + relmap.get(term));
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("-------------- children ----------------");
        //nkbc.buildChildren();
        it = this.children.keySet().iterator();
        while (it.hasNext()) {
            String rel = it.next();
            System.out.println("Relation: " + rel);
            Map<String,Set<String>> relmap = this.children.get(rel);
            Iterator<String> it2 = relmap.keySet().iterator();
            while (it2.hasNext()) {
                String term = it2.next();
                System.out.println(term + ": " + relmap.get(term));
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("-------------- disjoint ----------------");
        System.out.println(kb.kbCache.explicitDisjoint);
        System.out.println();
        System.out.println("-------------- domains ----------------");
        //nkbc.collectDomains();
        Iterator<String> it3 = this.relations.iterator();
        while (it3.hasNext()) {
            String rel = it3.next();
            ArrayList<String> domains = this.signatures.get(rel);
            System.out.println(rel + ": " + domains);
        }
        System.out.println();
        System.out.println("-------------- valences ----------------");
        for (String rel : this.valences.keySet()) {
            Integer arity = this.valences.get(rel);
            System.out.println(rel + ": " + arity);
        }
        System.out.println();
        System.out.println("-------------- signatures ----------------");
        for (String rel : this.signatures.keySet()) {
            ArrayList<String> sig = this.signatures.get(rel);
            System.out.println(rel + ": " + sig);
        }
        System.out.println();
        System.out.println("-------------- insts ----------------");
        for (String inst : this.insts)
            System.out.print(inst + ", ");
        System.out.println();
        System.out.println();
        System.out.println("-------------- instancesOf ----------------");
        for (String inst : this.instanceOf.keySet())
            System.out.println(inst + ": " + this.instanceOf.get(inst));
        System.out.println();
        System.out.println();
        System.out.println("-------------- instances ----------------");
        for (String inst : this.instances.keySet())
            System.out.println(inst + ": " + this.instances.get(inst));
    }

    /** *************************************************************
     */
    public static void showAll(KBcache nkbc) {

        System.out.println("KBcache.main(): transRels: " + nkbc.transRels);
        System.out.println("KBcache.main(): instTransRels: " + nkbc.instTransRels);
        System.out.println("KBcache.main(): instTransRels: " + nkbc.instanceOf);
        System.out.println("KBcache.main(): subclass signature: " + nkbc.signatures.get("subclass"));
        System.out.println("KBcache.main(): PrimaryColor: " + nkbc.instanceOf.get("PrimaryColor"));
        System.out.println("KBcache.main(): ColorAttribute: " + nkbc.instanceOf.get("ColorAttribute"));
        System.out.println("KBcache.main(): PrimaryColor: " + nkbc.getInstancesForType("PrimaryColor"));
        System.out.println("KBcache.main(): ColorAttribute: " + nkbc.getInstancesForType("ColorAttribute"));
        System.out.println("KBcache.main(): FormOfGovernment: " + nkbc.getInstancesForType("FormOfGovernment"));
    }

    /** *************************************************************
     */
    public static void main(String[] args) {

        //debug = true;
        KBmanager.getMgr().initializeOnce();
        KB kb = KBmanager.getMgr().getKB(KBmanager.getMgr().getPref("sumokbname"));
        System.out.println("**** Finished loading KB ***");
        KBcache nkbc = kb.kbCache;
        showAll(nkbc);
        String term = "Integer";
        Set<String> classes = nkbc.getChildClasses(term);
        System.out.println("KBcache.main(): children of " + term + ": " +
                classes);
        //nkbc.children = new HashMap<>();
        //nkbc.buildChildrenNew("Entity","subclass");
        term = "Integer";
        classes = nkbc.getChildClasses(term);
        System.out.println("KBcache.main(): children of " + term + ": " +
                classes);
        term = "PositiveInteger";
        classes = nkbc.getChildClasses(term);
        System.out.println("KBcache.main(): children of " + term + ": " +
                classes);
        term = "PositiveRealNumber";
        classes = nkbc.getChildClasses(term);
        System.out.println("KBcache.main(): children of " + term + ": " +
                classes);
        term = "NonnegativeRealNumber";
        classes = nkbc.getChildClasses(term);
        System.out.println("KBcache.main(): children of " + term + ": " +
                classes);
        term = "Number";
        classes = nkbc.getChildClasses(term);
        System.out.println("KBcache.main(): children of " + term + ": " +
                classes);
        term = "RealNumber";
        classes = nkbc.getChildClasses(term);
        System.out.println("KBcache.main(): children of " + term + ": " +
                classes);
        KBcache.showState(nkbc);
        /*
        String term = "Object";
        HashSet<String> classes = nkbc.getChildClasses(term);
        HashSet<String> instances = nkbc.getChildInstances(term);
        System.out.println("number of child classes of " + term + ": " + classes.size());
        System.out.println("KBcache.main(): children of " + term + ": " +
                classes);
        System.out.println("number of instances of " + term + ": " + instances.size());
        System.out.println("KBcache.main(): instances of " + term + ": " +
               instances);
        term = "Process";
        classes = nkbc.getChildClasses(term);
        instances = nkbc.getChildInstances(term);
        System.out.println("number of classes of " + term + ": " + classes.size());
        System.out.println("KBcache.main(): children of " + term + ": " +
                classes);
        System.out.println("number of instances of " + term + ": " + instances.size());
        System.out.println("KBcache.main(): instances of " + term + ": " +
                instances);

        System.out.println("KBcache.main(): " + nkbc.getCommonParent("Kicking","Pushing"));
*/
        //showAll(nkbc);

        /* List<Formula> forms = kb.ask("arg",0,"subrelation");
        for (Formula f : forms) {
            String rel = f.getArgument(1);
            System.out.println("is " + rel + " a relation: " + kb.isInstanceOf(rel,"Relation"));
        }
        */
    }
}
