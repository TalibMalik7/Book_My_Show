package com.example.Book_My_Show;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}

}


/*
extra api to make -
 basic-
 getmovie by id
 getallmovie
 all thrater list
 update password
 all movie in a theater..
 all ticket by a user..

 ---cmplx--
 find movie name with max shows..across all places
 get show time for thater and movie
 count of unique location of theater
 cancel ticket

 find all ticket sold for a movie across all theaters.
 */


/*
Infinite recursion - usually occurs by bidirectional mapping

You can solve this issue by 3 methods.

1 - Create a DTO and include only the fields that you want to display in the response.

2 - You can use the @JsonManagedReference and @JsonBackReference annotations.

E.g. Add the @JsonManagedReference annotation to the Statemaster model.

@JsonManagedReference
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="statemaster")
public Set<Districtmaster> getDistrictmasters() {
    return this.districtmasters;
}
Add the @JsonBackReference annotation to the Districtmaster model.

@JsonBackReference
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="district_of_state")
public Statemaster getStatemaster() {
    return this.statemaster;
}
3 - You can use the @JsonIgnore annotation on the getter or setter method.

@JsonIgnore
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="statemaster")
public Set<Districtmaster> getDistrictmasters() {
    return this.districtmasters;
}
However, this approach will omit the set of Districtmaster from the response.



@JsonManagedReference -> Manages the forward part of the reference and the fields marked by this annotation are the ones that get Serialised
@JsonBackReference -> Manages the reverse part of the reference and the fields/collections marked with this annotation are not serialised.
Use case: You have a one-many or many-many relationships in your entities/tables and not using the above would lead to errors like

Infinite Recursion and hence stackoverflow - > Could not write content: Infinite recursion (StackOverflowError)
The above errors occurs because Jackson (or someother similiar) tries to serialise both ends of the relationship and ends up in a recursion.

@JsonIgnore performs similiar functions but the above mentioned annotations are preferable.


 */