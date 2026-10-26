package io.github.anupamhore.dekhojob.location;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name= "locations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String city;

    @Column(length = 100)
    private String region;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name= "country_code", nullable = false)
    private Country country;


    @Column(precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(precision = 9, scale = 6)
    private BigDecimal longitude;

    public String displayName(){
        return city + ", " + country.getName();
    }
}
