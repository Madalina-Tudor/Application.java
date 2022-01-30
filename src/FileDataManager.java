import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.Random;


public class FileDataManager implements IDataLoader{
    public Random rand=new Random();
    @Override
    public Student[] createStudentsData() {
        try(FileInputStream fis = new FileInputStream("studenti.xml")) {
            XMLDecoder decoder = new XMLDecoder(fis);
            Student[] students = (Student[]) decoder.readObject();
            decoder.close();
            fis.close();
            return students;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Student[0];
    }

    @Override
    public Profesor[] createProfesorData() {
        try(FileInputStream fis = new FileInputStream("profesori.xml")) {
            XMLDecoder decoder = new XMLDecoder(fis);
            Profesor[] students = (Profesor[])decoder.readObject();
            decoder.close();
            fis.close();
            return students;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Profesor[0];
    }

    @Override
    public Curs[] createCoursesData() {
        try(FileInputStream fis = new FileInputStream("cursuri.xml")) {
            XMLDecoder decoder = new XMLDecoder(fis);
            Curs[] students = (Curs[]) decoder.readObject();
            decoder.close();
            fis.close();
            return students;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Curs[0];
    }
    public void gradeStudents() {
        for (Curs c: this.createCoursesData()) {
            for( Student s: c.studenti) {
                try {
                    c.noteazaStudent(s, 1 + rand.nextInt(10));
                } catch (Exception e) {

                }
            }
        }
    }
}
