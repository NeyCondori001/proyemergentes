package com.emergentes.entidades;

import com.emergentes.entidades.Publicacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-06-23T17:21:20")
@StaticMetamodel(Area.class)
public class Area_ { 

    public static volatile SingularAttribute<Area, String> descripcion;
    public static volatile SingularAttribute<Area, Integer> id;
    public static volatile SingularAttribute<Area, String> nombre;
    public static volatile ListAttribute<Area, Publicacion> publicacionList;

}