import java.util.Date;

public class Task {
     int id;
     String description;
     String status;
     Date createdAt;
     Date updatedAt;


     public Task(int id, String description, String status, Date createdAt, Date updatedAt) {
         this.id = id;
         this.description = description;
         this.status = status;
         this.createdAt = createdAt;
         this.updatedAt = updatedAt;
     }

     public int getId() {
         return id;
     }
     public void setId(int id) {
         this.id = id;
     }
     public String getDescription() {
         return description;
     }
     public void setDescription(String description) {
         this.description = description;
     }
     public String getStatus() {
         return status;
     }
     public void setStatus(String status) {
         this.status = status;
     }
     public Date getCreatedAt() {
         return createdAt;
     }
     public void setCreatedAt(Date createdAt) {
         this.createdAt = createdAt;
     }
     public Date getUpdatedAt() {
         return updatedAt;
     }


}
