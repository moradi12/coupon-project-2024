package beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


@Data
@AllArgsConstructor
public class Coupon {
    private final int id;
    private final int companyId;
    private final Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private Integer amount;
    private Double price;
    private String image;


    // Constructors
    }

    //
//    public Coupon(int id, int companyId, Category category, String title, String description, Date startDate, Date endDate, Integer amount, Double price, String image) {
//        this.id = id;
//        this.companyId = companyId;
//        this.category = category;
//        this.title = title;
//        this.description = description;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.amount = amount;
//        this.price = price;
//        this.image = image;
//    }
//
//// Getter methods
//public int getId() {
//    return id;
//}
//
//public int getCompanyId() {
//    return companyId;
//}
//
//public Category getCategory() {
//    return category;
//}
//
//public String getTitle() {
//    return title;
//}
//
//public String getDescription() {
//    return description;
//}
//
//public Date getStartDate() {
//    return startDate;
//}
//
//public Date getEndDate() {
//    return endDate;
//}
//
//public Integer getAmount() {
//    return amount;
//}
//
//public Double getPrice() {
//    return price;
//}
//
//public String getImage() {
//    return image;
//}
//
//// Setter methods
//public void setCategory(Category category) {
//    this.category = category;
//}
//
//public void setTitle(String title) {
//    this.title = title;
//}
//
//public void setDescription(String description) {
//    this.description = description;
//}
//
//public void setStartDate(Date startDate) {
//    this.startDate = startDate;
//}
//
//public void setEndDate(Date endDate) {
//    this.endDate = endDate;
//}
//
//public void setAmount(Integer amount) {
//    this.amount = amount;
//}
//
//public void setPrice(Double price) {
//    this.price = price;
//}
//
//public void setImage(String image) {
//    this.image = image;
//}
//
//// toString method
//@Override
//public String toString() {
//    return "Coupon{" +
//            "id=" + id +
//            ", companyId=" + companyId +
//            ", category=" + category +
//            ", title='" + title + '\'' +
//            ", description='" + description + '\'' +
//            ", startDate=" + startDate +
//            ", endDate=" + endDate +
//            ", amount=" + amount +
//            ", price=" + price +
//            ", image='" + image + '\'' +
//            '}';
////
