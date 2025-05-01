package backend.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lnName;
    private String lnLocation;
    private String lnHospital;
    private LocalDate lnDate;
    private Integer lnNumberOfPeopleServed;
    private Integer lnNumberOfSpecialties;
    private Double lnAmountPlannedToRaise;

}