package ru.vlsv.ShopSpring.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 10.11.2019
 * @link https://github.com/Centnerman
 */

@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	public Role(String name) {
		this.name = name;
	}
}
