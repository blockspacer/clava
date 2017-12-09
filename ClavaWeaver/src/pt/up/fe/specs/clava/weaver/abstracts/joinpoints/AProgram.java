package pt.up.fe.specs.clava.weaver.abstracts.joinpoints;

import org.lara.interpreter.weaver.interf.events.Stage;
import java.util.Optional;
import org.lara.interpreter.exception.AttributeException;
import javax.script.Bindings;
import java.util.List;
import org.lara.interpreter.exception.ActionException;
import pt.up.fe.specs.clava.weaver.abstracts.ACxxWeaverJoinPoint;
import org.lara.interpreter.weaver.interf.JoinPoint;
import java.util.stream.Collectors;
import java.util.Arrays;

/**
 * Auto-Generated class for join point AProgram
 * This class is overwritten by the Weaver Generator.
 * 
 * 
 * @author Lara Weaver Generator
 */
public abstract class AProgram extends ACxxWeaverJoinPoint {

    /**
     * Get value on attribute name
     * @return the attribute's value
     */
    public abstract String getNameImpl();

    /**
     * Get value on attribute name
     * @return the attribute's value
     */
    public final Object getName() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "name", Optional.empty());
        	}
        	String result = this.getNameImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "name", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "name", e);
        }
    }

    /**
     * true if the program was compiled with a C++ standard
     */
    public abstract Boolean getIsCxxImpl();

    /**
     * true if the program was compiled with a C++ standard
     */
    public final Object getIsCxx() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "isCxx", Optional.empty());
        	}
        	Boolean result = this.getIsCxxImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "isCxx", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "isCxx", e);
        }
    }

    /**
     * The name of the standard (e.g., c99, c++11)
     */
    public abstract String getStandardImpl();

    /**
     * The name of the standard (e.g., c99, c++11)
     */
    public final Object getStandard() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "standard", Optional.empty());
        	}
        	String result = this.getStandardImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "standard", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "standard", e);
        }
    }

    /**
     * The flag of the standard (e.g., -std=c++11)
     */
    public abstract String getStdFlagImpl();

    /**
     * The flag of the standard (e.g., -std=c++11)
     */
    public final Object getStdFlag() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "stdFlag", Optional.empty());
        	}
        	String result = this.getStdFlagImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "stdFlag", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "stdFlag", e);
        }
    }

    /**
     * Get value on attribute defaultFlags
     * @return the attribute's value
     */
    public abstract String[] getDefaultFlagsArrayImpl();

    /**
     * Get value on attribute defaultFlags
     * @return the attribute's value
     */
    public Bindings getDefaultFlagsImpl() {
        String[] stringArrayImpl0 = getDefaultFlagsArrayImpl();
        Bindings nativeArray0 = getWeaverEngine().getScriptEngine().toNativeArray(stringArrayImpl0);
        return nativeArray0;
    }

    /**
     * Get value on attribute defaultFlags
     * @return the attribute's value
     */
    public final Object getDefaultFlags() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "defaultFlags", Optional.empty());
        	}
        	Bindings result = this.getDefaultFlagsImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "defaultFlags", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "defaultFlags", e);
        }
    }

    /**
     * Get value on attribute userFlags
     * @return the attribute's value
     */
    public abstract String[] getUserFlagsArrayImpl();

    /**
     * Get value on attribute userFlags
     * @return the attribute's value
     */
    public Bindings getUserFlagsImpl() {
        String[] stringArrayImpl0 = getUserFlagsArrayImpl();
        Bindings nativeArray0 = getWeaverEngine().getScriptEngine().toNativeArray(stringArrayImpl0);
        return nativeArray0;
    }

    /**
     * Get value on attribute userFlags
     * @return the attribute's value
     */
    public final Object getUserFlags() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "userFlags", Optional.empty());
        	}
        	Bindings result = this.getUserFlagsImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "userFlags", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "userFlags", e);
        }
    }

    /**
     * Get value on attribute includeFolders
     * @return the attribute's value
     */
    public abstract String[] getIncludeFoldersArrayImpl();

    /**
     * Get value on attribute includeFolders
     * @return the attribute's value
     */
    public Bindings getIncludeFoldersImpl() {
        String[] stringArrayImpl0 = getIncludeFoldersArrayImpl();
        Bindings nativeArray0 = getWeaverEngine().getScriptEngine().toNativeArray(stringArrayImpl0);
        return nativeArray0;
    }

    /**
     * Get value on attribute includeFolders
     * @return the attribute's value
     */
    public final Object getIncludeFolders() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "includeFolders", Optional.empty());
        	}
        	Bindings result = this.getIncludeFoldersImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "includeFolders", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "includeFolders", e);
        }
    }

    /**
     * Get value on attribute baseFolder
     * @return the attribute's value
     */
    public abstract String getBaseFolderImpl();

    /**
     * Get value on attribute baseFolder
     * @return the attribute's value
     */
    public final Object getBaseFolder() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "baseFolder", Optional.empty());
        	}
        	String result = this.getBaseFolderImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "baseFolder", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "baseFolder", e);
        }
    }

    /**
     * Get value on attribute weavingFolder
     * @return the attribute's value
     */
    public abstract String getWeavingFolderImpl();

    /**
     * Get value on attribute weavingFolder
     * @return the attribute's value
     */
    public final Object getWeavingFolder() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "weavingFolder", Optional.empty());
        	}
        	String result = this.getWeavingFolderImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "weavingFolder", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "weavingFolder", e);
        }
    }

    /**
     * Method used by the lara interpreter to select files
     * @return 
     */
    public abstract List<? extends AFile> selectFile();

    /**
     * 
     */
    public void rebuildImpl() {
        throw new UnsupportedOperationException(get_class()+": Action rebuild not implemented ");
    }

    /**
     * 
     */
    public final void rebuild() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "rebuild", this, Optional.empty());
        	}
        	this.rebuildImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "rebuild", this, Optional.empty());
        	}
        } catch(Exception e) {
        	throw new ActionException(get_class(), "rebuild", e);
        }
    }

    /**
     * 
     * @param file 
     */
    public void addFileImpl(AFile file) {
        throw new UnsupportedOperationException(get_class()+": Action addFile not implemented ");
    }

    /**
     * 
     * @param file 
     */
    public final void addFile(AFile file) {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "addFile", this, Optional.empty(), file);
        	}
        	this.addFileImpl(file);
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "addFile", this, Optional.empty(), file);
        	}
        } catch(Exception e) {
        	throw new ActionException(get_class(), "addFile", e);
        }
    }

    /**
     * 
     */
    public void pushImpl() {
        throw new UnsupportedOperationException(get_class()+": Action push not implemented ");
    }

    /**
     * 
     */
    public final void push() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "push", this, Optional.empty());
        	}
        	this.pushImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "push", this, Optional.empty());
        	}
        } catch(Exception e) {
        	throw new ActionException(get_class(), "push", e);
        }
    }

    /**
     * 
     */
    public void popImpl() {
        throw new UnsupportedOperationException(get_class()+": Action pop not implemented ");
    }

    /**
     * 
     */
    public final void pop() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "pop", this, Optional.empty());
        	}
        	this.popImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "pop", this, Optional.empty());
        	}
        } catch(Exception e) {
        	throw new ActionException(get_class(), "pop", e);
        }
    }

    /**
     * 
     */
    @Override
    public final List<? extends JoinPoint> select(String selectName) {
        List<? extends JoinPoint> joinPointList;
        switch(selectName) {
        	case "file": 
        		joinPointList = selectFile();
        		break;
        	default:
        		joinPointList = super.select(selectName);
        		break;
        }
        return joinPointList;
    }

    /**
     * 
     */
    @Override
    protected final void fillWithAttributes(List<String> attributes) {
        super.fillWithAttributes(attributes);
        attributes.add("name");
        attributes.add("isCxx");
        attributes.add("standard");
        attributes.add("stdFlag");
        attributes.add("defaultFlags");
        attributes.add("userFlags");
        attributes.add("includeFolders");
        attributes.add("baseFolder");
        attributes.add("weavingFolder");
    }

    /**
     * 
     */
    @Override
    protected final void fillWithSelects(List<String> selects) {
        super.fillWithSelects(selects);
        selects.add("file");
    }

    /**
     * 
     */
    @Override
    protected final void fillWithActions(List<String> actions) {
        super.fillWithActions(actions);
        actions.add("void rebuild()");
        actions.add("void addFile(file)");
        actions.add("void push()");
        actions.add("void pop()");
    }

    /**
     * Returns the join point type of this class
     * @return The join point type
     */
    @Override
    public final String get_class() {
        return "program";
    }
    /**
     * 
     */
    protected enum ProgramAttributes {
        NAME("name"),
        ISCXX("isCxx"),
        STANDARD("standard"),
        STDFLAG("stdFlag"),
        DEFAULTFLAGS("defaultFlags"),
        USERFLAGS("userFlags"),
        INCLUDEFOLDERS("includeFolders"),
        BASEFOLDER("baseFolder"),
        WEAVINGFOLDER("weavingFolder"),
        PARENT("parent"),
        ASTANCESTOR("astAncestor"),
        AST("ast"),
        CODE("code"),
        ISINSIDELOOPHEADER("isInsideLoopHeader"),
        LINE("line"),
        ASTNUMCHILDREN("astNumChildren"),
        TYPE("type"),
        ROOT("root"),
        JAVAVALUE("javaValue"),
        CHAINANCESTOR("chainAncestor"),
        CHAIN("chain"),
        JOINPOINTTYPE("joinpointType"),
        CURRENTREGION("currentRegion"),
        ANCESTOR("ancestor"),
        HASASTPARENT("hasAstParent"),
        ASTCHILD("astChild"),
        PARENTREGION("parentRegion"),
        ASTNAME("astName"),
        ASTID("astId"),
        CONTAINS("contains"),
        JAVAFIELDS("javaFields"),
        ASTPARENT("astParent"),
        SETUSERFIELD("setUserField"),
        JAVAFIELDTYPE("javaFieldType"),
        LOCATION("location"),
        GETUSERFIELD("getUserField"),
        HASPARENT("hasParent");
        private String name;

        /**
         * 
         */
        private ProgramAttributes(String name){
            this.name = name;
        }
        /**
         * Return an attribute enumeration item from a given attribute name
         */
        public static Optional<ProgramAttributes> fromString(String name) {
            return Arrays.asList(values()).stream().filter(attr -> attr.name.equals(name)).findAny();
        }

        /**
         * Return a list of attributes in String format
         */
        public static List<String> getNames() {
            return Arrays.asList(values()).stream().map(ProgramAttributes::name).collect(Collectors.toList());
        }

        /**
         * True if the enum contains the given attribute name, false otherwise.
         */
        public static boolean contains(String name) {
            return fromString(name).isPresent();
        }
    }
}
