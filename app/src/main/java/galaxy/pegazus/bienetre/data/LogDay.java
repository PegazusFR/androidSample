package galaxy.pegazus.bienetre.data;

import android.provider.BaseColumns;

import java.sql.Date;

/**
 * Created by yklausz on 08/05/2015.
 */
public class LogDay {

    /* Inner class that defines the table contents */
    public static abstract class LogDayEntry implements BaseColumns {
        public static final String TABLE_NAME = "LOG_DAY_ENTRY";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_COMMENT = "comment";
        public static final String COLUMN_NAME_ASSESSMENT = "assessment";
    }

    private Integer _id;
    private Integer date;
    private String comment;
    private Integer assessment;

    public String getComment() {
        return comment;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getAssessment() {
        return assessment;
    }

    public void setAssessment(Integer assessment) {
        this.assessment = assessment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }


}
