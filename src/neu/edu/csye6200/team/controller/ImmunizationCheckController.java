package neu.edu.csye6200.team.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import neu.edu.csye6200.main.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import neu.edu.csye6200.team.data.ImmuDataManagement;
import neu.edu.csye6200.team.objects.ImmunList;
import neu.edu.csye6200.team.objects.Immunization;
import neu.edu.csye6200.team.objects.Student;

/**
 * 
 * @author Qianru
 * The class contains operations in Immunization Check interface
 */
public class ImmunizationCheckController extends AbstractController  implements Initializable {

	private Main application;

	public static Student student;
	@FXML
	private TableView immTable = new TableView();
	@FXML
	private TextField txtStuid = new TextField();
	@FXML
	private TextField txtStuname = new TextField();
	@FXML
	private TextField txtImmname = new TextField();
	@FXML
	private TextField txtImmid = new TextField();
	@FXML
	private TextField txtImmdate = new TextField();
	@FXML
	private TextField txtDuration = new TextField();
	
    
	private BackgroundPanelController background_controller;
	
    public void setBackground(BackgroundPanelController controller) {
    	background_controller = controller;
    }
    
    
	ImmuDataManagement idm;
	ArrayList<Immunization> immList;
	private ObservableList<ImmunList> data = FXCollections.observableArrayList();
	ObservableList<TableColumn> observableList;
	
	public void setApp(Main app) {
		this.application = app;
		System.out.println(application+"    in setApp");
	}
	
    public void setStudent(Student student) {
    	this.student = student;
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	       // TODO (don't really need to do anything here).
		observableList = immTable.getColumns();
		observableList.get(0).setCellValueFactory(new PropertyValueFactory("immID"));
		observableList.get(1).setCellValueFactory(new PropertyValueFactory("immName"));
		observableList.get(2).setCellValueFactory(new PropertyValueFactory("status"));
		immTable.setItems(data);
		immTable.getItems().clear();
		
		idm = new ImmuDataManagement();
		System.out.println(student+"    when initial");
		immList = (ArrayList)idm.getDataList(student.getStuId());
		List<ImmunList> dataImmlist = new ArrayList<ImmunList>();
		for(Immunization immun : immList) {
			String id = String.valueOf(immun.getImmuId());
			String name = immun.getImmuName();
			int dur =immun.getDuration();
			int daydur = dur*365;
			Date imd = immun.getImmuDate();
			String stat;
			
			//Date imd = toDate(date,"MM-dd-yyyy");
			Date now= new Date();
			int timePast = daysPast(imd,now);
		    System.out.println("the days past:  "+timePast);
		    int timeLeft = daydur - timePast;
		    if(timeLeft<0) {
		    	stat = "Out Of Date";
		    	
		    }
		    else if(timeLeft>=0 && timeLeft<30) {
		    	stat = "Warning";
		    }
		    else {
		    	stat = "Normal";
		    }
		    
			ImmunList imm = new ImmunList();
			imm.setImmID(id);
			imm.setImmName(name);
			imm.setStatus(stat);
			dataImmlist.add(imm);
			
		}
		data.addAll(dataImmlist);
		immTable.setItems(data);
		
		
	}
	
	private int daysPast(Date date1, Date date2) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();  
        cal.setTime(date2);  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(date1);  
        long time2 = cal.getTimeInMillis();       

        long between_days;
        between_days=(time1-time2)/(1000*3600*24);  
        
        return Integer.parseInt(String.valueOf(between_days)); 
	}

	
	public void checkImmun(ActionEvent event) {
		int index = immTable.getSelectionModel().getFocusedIndex();
		Immunization selimm = immList.get(index);
		String stuid = String.valueOf(selimm.getStuId());
		String stuname = student.firstName +" "+ student.lastName;
		String imid = String.valueOf(selimm.getImmuId());
		String imname = selimm.getImmuName();
		Date imdate = selimm.getImmuDate();
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
	    String idate = df.format(imdate);
	    String duration = String.valueOf(selimm.getDuration());
	    
	    txtStuid.setText(stuid);
		txtStuname.setText(stuname);
		txtImmname.setText(imname);
		txtImmid.setText(imid);
		txtImmdate.setText(idate);
		txtDuration.setText(duration);
	}
	
	public void back(ActionEvent event) throws Exception {
		background_controller.loadStudent();
	}
	
}
