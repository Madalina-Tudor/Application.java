import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Application {
    private static Application single_instance = null;
    private List<User> userList = new ArrayList<>();

    public User currentUser = null;

    static Application getInstance() {
        if ( single_instance == null) {
            single_instance = new Application();
        }
        return  single_instance;
    }

    private Application() {
         /* HardcodatDataManager dataManager = new HardcodatDataManager();
        Random r = new Random();
        var studenti = dataManager.dataSetOfStudent;
        var profesori = dataManager.dataSetOfProfesor;
        this.userList.add(new User("aaa", "aaa", new StudentStrategy( studenti[r.nextInt(studenti.length)])));
        this.userList.add(new User("bbb", "aaa", new TeacherStrategy( profesori[r.nextInt(profesori.length)])));
        this.userList.add(new User("ccc", "ccc", new StudentStrategy( studenti[r.nextInt(studenti.length)])));
        this.userList.add(new User("ddd", "ddd", new TeacherStrategy( profesori[r.nextInt(profesori.length)])));
        this.userList.add(new User("eee", "eee", new StudentStrategy( studenti[r.nextInt(studenti.length)])));
        try {
            FileOutputStream fos = new FileOutputStream("users.xml");
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
            });
            encoder.writeObject(userList);
            encoder.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } */
        this.initUsers();
    }

    private void initUsers() {
        try (FileInputStream fis = new FileInputStream("users.xml")) {
            XMLDecoder decoder = new XMLDecoder(fis);
            this.userList = (ArrayList<User>)decoder.readObject();
            decoder.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(User user) throws Exception {
        int index = userList.indexOf(user);
        if ( index != -1 ) {
            Application.getInstance().currentUser = userList.get(index);
        } else {
            throw new Exception("Username sau parola este gresita!");
        }
    }
    public int register(String username,String parola,String nume,String prenume){
        int result=0;
        FileDataManager data = new FileDataManager();
        Curs[] cursuri = data.createCoursesData();
        try {
            FileOutputStream fos = new FileOutputStream("users.xml");
            XMLEncoder encoder = new XMLEncoder(fos);
            if(userList.contains(new User(nume,prenume))){
                result=-1;
            }
            for(int i=0;i< cursuri.length;i++){
                for(Student s:cursuri[i].studenti){
                    if(s.nume.compareTo(nume)==0 && s.prenume.compareTo(prenume)==0){
                        result=-2;
                    }
                }
                if(cursuri[i].profu.equals(new Profesor(nume,prenume))){
                    result=-3;
                }
            }
            if(result == -2){
                userList.add(new User(username,parola,new StudentStrategy(new Student(nume,prenume))));
            }else if(result == -3){
                userList.add(new User(username,parola,new TeacherStrategy(new Profesor(nume,prenume))));
            }
            encoder.writeObject(userList);
            encoder.close();
            fos.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }
}
