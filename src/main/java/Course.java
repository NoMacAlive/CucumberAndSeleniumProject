import java.util.*;

public class Course {
	private Database db;
	private int id;
	private List<TimeSlot> _timeslots;
	private String _time;
	private String _location;
	private String _description;
    public Course(){

    }

	public Course(Database db) {
		this.db = db;
	}
	
	public Course(int id, List<TimeSlot> ts) {
		this.id = id;
		this._timeslots = ts;
	}

	public Course(Database db, int course_id) {
		this.db = db;
    	this.id = course_id;
		this._location = this.db.getCourseLocation(this.id);
		this._description = this.db.getDescription(this.id);
    }

	public Course(int id, String description,int capacity,boolean concession,List<String> compulsory_program,List<String> prerequisite,List<String> timeslots,String location,String status) {
		this.id = id;
		this._description = description;
		this._location = location;
	}

    public Boolean isValidForConcession() {
        return true;
    }

	public int getCourseId() {
    	return this.id;
	}

	public String getCourseDescription() {
		if (this.getCourseId() != -1) {
			return this._description;
		} else {
			throw new RuntimeException("Course is not exist");
		}
	}

	public boolean create(String description,int capacity,boolean concession,List<String> compulsory_program,List<String> prerequisite,List<String> timeslots,String location,String status) {
		if (description == null || description.equals("")) {
			throw new RuntimeException("Course description is empty");
		} else if (capacity <= 0) {
			throw new RuntimeException("Course capacity is invalid");
		} else if (timeslots == null || timeslots.size() <= 0) {
			throw new RuntimeException("Course timeslots is empty");
		} else if (location == null || location.equals("")) {
			throw new RuntimeException("Course location is empty");
		} else if (status == null || status.equals("")) {
			throw new RuntimeException("Course status is empty");
		} else {
			return this.db.createCourse(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status);
		}
	}
	
	

	public List<TimeSlot> getTimeSlots(int courseId){
		if(courseId == this.id) {
			return this._timeslots; 
		}
		throw new RuntimeException("Course status is empty");
		
	}

	public String selectTimeSlot(int courseId) {
		return this.db.selectTimeSlot(this.id);
	}

	public String getLocation(){
		if (this.getCourseId() != -1) {
			return this._location;
		}else{
			throw new RuntimeException("Course is not exist");
		}
	}

	public boolean checkTimetableClash(int courseId, int timeslotId) {
		 return this.db.checkTimetableClash(courseId,timeslotId);
	}

	public int getCourseCapacityForTimeSlot(int courseId, int timeslotId) {
		return this.db.getCourseCapacityForTimeSlot( courseId,  timeslotId);
	}

	public List<Integer> getCourseCapacityForCompulsory(int courseId, int timeslotId) {
		return this.db.getCourseCapacityForCompulsory( courseId,  timeslotId);
	}

	public List<Integer> getCourseCapacityForElective(int courseId, int timeslotId) {
		return this.db.getCourseCapacityForElective( courseId,  timeslotId);
	}

	public List<Course> getPrerequestCourse(int courseId) {
		return this.db.getPrerequestCourse( courseId);
	}

	public List<Course> getConflictCourse(int courseId) {
		return this.db.getConflictCourse( courseId);
	}

	public boolean checkEligibility(Course course, User user) {
		return this.db.checkEligibility(course,user);
	}

}
