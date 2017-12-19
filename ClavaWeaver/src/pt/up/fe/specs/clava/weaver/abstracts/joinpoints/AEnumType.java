package pt.up.fe.specs.clava.weaver.abstracts.joinpoints;

import org.lara.interpreter.weaver.interf.events.Stage;
import java.util.Optional;
import org.lara.interpreter.exception.AttributeException;
import java.util.Map;
import java.util.List;
import org.lara.interpreter.weaver.interf.JoinPoint;
import java.util.stream.Collectors;
import java.util.Arrays;

/**
 * Auto-Generated class for join point AEnumType
 * This class is overwritten by the Weaver Generator.
 * 
 * 
 * @author Lara Weaver Generator
 */
public abstract class AEnumType extends AType {

    protected AType aType;

    /**
     * 
     */
    public AEnumType(AType aType){
        this.aType = aType;
    }
    /**
     * Get value on attribute integerType
     * @return the attribute's value
     */
    public abstract AJoinPoint getIntegerTypeImpl();

    /**
     * Get value on attribute integerType
     * @return the attribute's value
     */
    public final Object getIntegerType() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "integerType", Optional.empty());
        	}
        	AJoinPoint result = this.getIntegerTypeImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "integerType", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "integerType", e);
        }
    }

    /**
     * Get value on attribute kind
     * @return the attribute's value
     */
    @Override
    public String getKindImpl() {
        return this.aType.getKindImpl();
    }

    /**
     * Get value on attribute isTopLevel
     * @return the attribute's value
     */
    @Override
    public Boolean getIsTopLevelImpl() {
        return this.aType.getIsTopLevelImpl();
    }

    /**
     * Get value on attribute isArray
     * @return the attribute's value
     */
    @Override
    public Boolean getIsArrayImpl() {
        return this.aType.getIsArrayImpl();
    }

    /**
     * Get value on attribute isPointer
     * @return the attribute's value
     */
    @Override
    public Boolean getIsPointerImpl() {
        return this.aType.getIsPointerImpl();
    }

    /**
     * Get value on attribute arraySize
     * @return the attribute's value
     */
    @Override
    public Integer getArraySizeImpl() {
        return this.aType.getArraySizeImpl();
    }

    /**
     * Get value on attribute elementType
     * @return the attribute's value
     */
    @Override
    public AJoinPoint getElementTypeImpl() {
        return this.aType.getElementTypeImpl();
    }

    /**
     * Get value on attribute hasTemplateArgs
     * @return the attribute's value
     */
    @Override
    public Boolean getHasTemplateArgsImpl() {
        return this.aType.getHasTemplateArgsImpl();
    }

    /**
     * Get value on attribute templateArgsArrayImpl
     * @return the attribute's value
     */
    @Override
    public String[] getTemplateArgsArrayImpl() {
        return this.aType.getTemplateArgsArrayImpl();
    }

    /**
     * Get value on attribute hasSugar
     * @return the attribute's value
     */
    @Override
    public Boolean getHasSugarImpl() {
        return this.aType.getHasSugarImpl();
    }

    /**
     * Get value on attribute desugar
     * @return the attribute's value
     */
    @Override
    public AJoinPoint getDesugarImpl() {
        return this.aType.getDesugarImpl();
    }

    /**
     * Get value on attribute isBuiltin
     * @return the attribute's value
     */
    @Override
    public Boolean getIsBuiltinImpl() {
        return this.aType.getIsBuiltinImpl();
    }

    /**
     * Get value on attribute constant
     * @return the attribute's value
     */
    @Override
    public Boolean getConstantImpl() {
        return this.aType.getConstantImpl();
    }

    /**
     * Get value on attribute unwrap
     * @return the attribute's value
     */
    @Override
    public AType getUnwrapImpl() {
        return this.aType.getUnwrapImpl();
    }

    /**
     * 
     * @param node 
     */
    @Override
    public AJoinPoint replaceWithImpl(AJoinPoint node) {
        return this.aType.replaceWithImpl(node);
    }

    /**
     * 
     * @param node 
     */
    @Override
    public AJoinPoint insertBeforeImpl(AJoinPoint node) {
        return this.aType.insertBeforeImpl(node);
    }

    /**
     * 
     * @param node 
     */
    @Override
    public AJoinPoint insertBeforeImpl(String node) {
        return this.aType.insertBeforeImpl(node);
    }

    /**
     * 
     * @param node 
     */
    @Override
    public AJoinPoint insertAfterImpl(AJoinPoint node) {
        return this.aType.insertAfterImpl(node);
    }

    /**
     * 
     * @param code 
     */
    @Override
    public AJoinPoint insertAfterImpl(String code) {
        return this.aType.insertAfterImpl(code);
    }

    /**
     * 
     */
    @Override
    public void detachImpl() {
        this.aType.detachImpl();
    }

    /**
     * 
     * @param type 
     */
    @Override
    public void setTypeImpl(AJoinPoint type) {
        this.aType.setTypeImpl(type);
    }

    /**
     * 
     */
    @Override
    public AJoinPoint copyImpl() {
        return this.aType.copyImpl();
    }

    /**
     * 
     * @param fieldName 
     * @param value 
     */
    @Override
    public Object setUserFieldImpl(String fieldName, Object value) {
        return this.aType.setUserFieldImpl(fieldName, value);
    }

    /**
     * 
     * @param fieldNameAndValue 
     */
    @Override
    public Object setUserFieldImpl(Map<?, ?> fieldNameAndValue) {
        return this.aType.setUserFieldImpl(fieldNameAndValue);
    }

    /**
     * 
     * @param message 
     */
    @Override
    public void messageToUserImpl(String message) {
        this.aType.messageToUserImpl(message);
    }

    /**
     * 
     * @param position 
     * @param code 
     */
    @Override
    public void insertImpl(String position, String code) {
        this.aType.insertImpl(position, code);
    }

    /**
     * 
     * @param attribute 
     * @param value 
     */
    @Override
    public void defImpl(String attribute, Object value) {
        this.aType.defImpl(attribute, value);
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return this.aType.toString();
    }

    /**
     * 
     */
    @Override
    public Optional<? extends AType> getSuper() {
        return Optional.of(this.aType);
    }

    /**
     * 
     */
    @Override
    public final List<? extends JoinPoint> select(String selectName) {
        List<? extends JoinPoint> joinPointList;
        switch(selectName) {
        	default:
        		joinPointList = this.aType.select(selectName);
        		break;
        }
        return joinPointList;
    }

    /**
     * 
     */
    @Override
    protected final void fillWithAttributes(List<String> attributes) {
        this.aType.fillWithAttributes(attributes);
        attributes.add("integerType");
    }

    /**
     * 
     */
    @Override
    protected final void fillWithSelects(List<String> selects) {
        this.aType.fillWithSelects(selects);
    }

    /**
     * 
     */
    @Override
    protected final void fillWithActions(List<String> actions) {
        this.aType.fillWithActions(actions);
    }

    /**
     * Returns the join point type of this class
     * @return The join point type
     */
    @Override
    public final String get_class() {
        return "enumType";
    }

    /**
     * Defines if this joinpoint is an instanceof a given joinpoint class
     * @return True if this join point is an instanceof the given class
     */
    @Override
    public final boolean instanceOf(String joinpointClass) {
        boolean isInstance = get_class().equals(joinpointClass);
        if(isInstance) {
        	return true;
        }
        return this.aType.instanceOf(joinpointClass);
    }
    /**
     * 
     */
    protected enum EnumTypeAttributes {
        INTEGERTYPE("integerType"),
        KIND("kind"),
        ISTOPLEVEL("isTopLevel"),
        ISARRAY("isArray"),
        ISPOINTER("isPointer"),
        ARRAYSIZE("arraySize"),
        ELEMENTTYPE("elementType"),
        HASTEMPLATEARGS("hasTemplateArgs"),
        TEMPLATEARGS("templateArgs"),
        HASSUGAR("hasSugar"),
        DESUGAR("desugar"),
        ISBUILTIN("isBuiltin"),
        CONSTANT("constant"),
        UNWRAP("unwrap"),
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
        JAVAFIELDTYPE("javaFieldType"),
        USERFIELD("userField"),
        LOCATION("location"),
        GETUSERFIELD("getUserField"),
        HASPARENT("hasParent");
        private String name;

        /**
         * 
         */
        private EnumTypeAttributes(String name){
            this.name = name;
        }
        /**
         * Return an attribute enumeration item from a given attribute name
         */
        public static Optional<EnumTypeAttributes> fromString(String name) {
            return Arrays.asList(values()).stream().filter(attr -> attr.name.equals(name)).findAny();
        }

        /**
         * Return a list of attributes in String format
         */
        public static List<String> getNames() {
            return Arrays.asList(values()).stream().map(EnumTypeAttributes::name).collect(Collectors.toList());
        }

        /**
         * True if the enum contains the given attribute name, false otherwise.
         */
        public static boolean contains(String name) {
            return fromString(name).isPresent();
        }
    }
}
