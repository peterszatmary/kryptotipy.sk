package sk.kryptotipy.web.data.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Entity
@ToString(exclude = {"id"})
@Table(name = "btc_price")
@JsonIgnoreProperties({ "id"})
public class BtcPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private Double price;

    @Setter
    private LocalDateTime time;
}