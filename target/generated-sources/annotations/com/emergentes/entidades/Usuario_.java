package com.emergentes.entidades;

import com.emergentes.entidades.Publicacion;
import com.emergentes.entidades.Solicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-06-23T17:21:20")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> apellido;
    public static volatile SingularAttribute<Usuario, String> fechanac;
    public static volatile SingularAttribute<Usuario, Integer> celular;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, Character> sexo;
    public static volatile ListAttribute<Usuario, Solicitud> solicitudList;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile ListAttribute<Usuario, Publicacion> publicacionList;
    public static volatile SingularAttribute<Usuario, String> email;

}