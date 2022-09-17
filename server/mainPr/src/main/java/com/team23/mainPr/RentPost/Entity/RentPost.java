package com.team23.mainPr.RentPost.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;



/**<pre>
 * \@GeneratedValue(strategy = GenerationType.IDENTITY)는 적절하지 않다. - 불필요한 DB 엑세스 발생, 이후 해시값 사용 등 정말 유니크한 값을 만드는 방식을 취해야 한다.
 * </pre>
 * */

@Entity
@Data
public class RentPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentPostId;
    private String rentPostContents;
    private String rentPostName;
    private ZonedDateTime writeDate;
    private ZonedDateTime updateDate;
    Boolean rented = false;
}
