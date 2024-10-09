package models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserModel {

    private Integer page;

    private Integer perPage;

    private Integer total;

    private Integer totalPages;

    private List<Datum> data = null;

}
