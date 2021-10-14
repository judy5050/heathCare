package ga.healtCare.src.hospitalInfo.model;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@Entity
@Table(name = "HospitalInfo")
public class HospitalInfo {

    @Id
    @Column(name = "hospitalIdx", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hospitalName")
    private String hospitalName;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

}
