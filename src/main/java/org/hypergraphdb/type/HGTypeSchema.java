package org.hypergraphdb.type;

import java.net.URI;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HyperGraph;

public interface HGTypeSchema<TypeDescriptor>
{
    /**
     * <p>
     * Return the name of this <code>HGTypeSchema</code>. The name of a type
     * schema uniquely identifies it.
     * </p>
     */
    String getName();

    /**
     * <p>
     * Initialize the schema with the {@link HyperGraph} instance to which it is bound.
     * A given schema runtime instance is only bound to one database instance. 
     * </p>
     * 
     * @param graph
     */
    void initialize(HyperGraph graph);
    
    /**
     * <p>Return the {@link HGHandle} of an existing HyperGraph type that corresponds
     * to the specified type identifier according to this schema.</p>
     *  
     * @param typeId The identifier of the type. The identifier must be valid
     * within this schema. In particular the schema part of the URI must match
     * this schema's name.
     * @return The {@link HGHandle} of the HyperGraph type if it exists or <code>null</code> 
     * if it doesn't.
     */
    HGHandle findType(URI typeId);
    
    /**
     * <p>
     * Construct a new HyperGraphDB type from the specified type identifier<code>URI</code>.
     * It is the responsibility of the schema implementation to find the correct 
     * <code>TypeDescriptor</code> for that identifier. The schema may return an existing
     * HyperGraph type that corresponds to the identifier is it finds one already
     * in the database. However, types, like other atoms, are not guaranteed to be 
     * immutable. Therefore, a schema does not "own" a HyperGraph type and the latter
     * may be modified outside of its control.
     * </p>
     * 
     * @param typeId The identifier of the type. The identifier must be valid
     * within this schema. In particular the schema part of the URI must match
     * this schema's name.
     * @return The {@link HGHandle} of a newly created or existing HyperGraph type
     * that corresponds to the passed in type identifier.
     */
    void defineType(URI typeId, HGHandle typeHandle);

    void removeType(URI typeId);
    
    /**
     * <p>
     * Return the <code>TypeDescriptor</code> corresponding to the passed in type
     * identifier.
     * </p>
     *  
     * @param typeId
     * @return
     */
    TypeDescriptor getTypeDescriptor(URI typeId);    
    
    HGAtomType toRuntimeType(HGHandle typeHandle, HGAtomType typeInstance);
    
    URI toTypeURI(Object object);
    URI toTypeURI(Class<?> javaClass);
}