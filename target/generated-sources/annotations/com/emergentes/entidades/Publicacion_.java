package com.emergentes.entidades;

import com.emergentes.entidades.Area;
import com.emergentes.entidades.Categoria;
import com.emergentes.entidades.Empresa;
import com.emergentes.entidades.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-06-23T17:21:20")
@StaticMetamodel(Publicacion.class)
public class Publicacion_ { 

    public static volatile SingularAttribute<Publicacion, String> descripcion;
    public static volatile SingularAttribute<Publicacion, Area> idArea;
    public static volatile SingularAttribute<Publicacion, Usuario> idUsuario;
    public static volatile SingularAttribute<Publicacion, String> titulo;
    public static volatile SingularAttribute<Publicacion, Empresa> idEmpresa;
    public static volatile SingularAttribute<Publicacion, Integer> id;
    public static volatile SingularAttribute<Publicacion, Categoria> idCategoria;

}