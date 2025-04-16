package edu.comillas.icai.gitt.pat.spring.p5.entity;

import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import jakarta.persistence.*;


/**
 * TODO#2
 * Completa la entidad AppUser (cuya tabla en BD se llamará APP_USER)
 * para que tenga los siguientes campos obligatorios:
 * - id, que será la clave primaria numérica y autogenerada
 * - email, que debe tener valores únicos en toda la tabla
 * - password
 * - role, modelado con la clase Role ya existente en el proyecto
 * - name
 */

@Entity
@Table(name = "APP_USER", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class AppUser {
    //Todos son obligatorios asi que no pueden ser nulls
    //Clave id
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public long id;
    //email, tiene que ser unico
    @Column(nullable = false, unique = true) public String email;
    //contra
    @Column(nullable = false) public String password;
    //Role con la clase
    @Column(nullable = false) public Role role;
    //Nombre
    @Column(nullable = false) public String name;
}