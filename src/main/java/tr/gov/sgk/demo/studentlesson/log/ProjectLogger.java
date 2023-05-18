package tr.gov.sgk.demo.studentlesson.log;

import org.apache.logging.log4j.LogManager;
import tr.gov.sgk.demo.studentlesson.dto.LessonDTO;
import tr.gov.sgk.demo.studentlesson.dto.StudentDTO;
import tr.gov.sgk.demo.studentlesson.dto.StudentNotesDTO;
import org.apache.logging.log4j.Logger;

public class ProjectLogger {
    private static final Logger logger = LogManager.getLogger(ProjectLogger.class);

    public void addStudent(StudentDTO student) {
        // some code here to add student
        try {
            logger.debug("Student added: " + student.getFirstName() + " " + student.getLastName());
            logger.info("Student added: " + student.getFirstName() + " " + student.getLastName());
            logger.warn("Student added: " + student.getFirstName() + " " + student.getLastName());
            logger.error("Student added: " + student.getFirstName() + " " + student.getLastName());
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void updateStudent(StudentDTO student) {
        // some code here to update student
        logger.debug("Student updated: " + student.getFirstName() + " " + student.getLastName());
    }

    public void deleteStudent(StudentDTO student) {
        // some code here to delete student
        logger.debug("Student deleted: " + student.getFirstName() + " " + student.getLastName());
    }

    public void addLesson(LessonDTO lesson) {
        // some code here to add lesson
        logger.debug("Lesson added: " + lesson.getLessonName());
    }

    public void updateLesson(LessonDTO lesson) {
        // some code here to update lesson
        logger.debug("Lesson updated: " + lesson.getLessonName());
    }

    public void deleteLesson(LessonDTO lesson) {
        // some code here to delete lesson
        logger.debug("Lesson deleted: " + lesson.getLessonName());
    }

    public void addNote(StudentNotesDTO note) {
        // some code here to add note
        logger.debug("Note added: " + note.getStudent().getFirstName() + " " + note.getStudent().getLastName() +
                " - " + note.getLesson().getLessonName());
    }

    public void updateNote(StudentNotesDTO note) {
        // some code here to update note
        logger.debug("Note updated: " + note.getStudent().getFirstName() + " " + note.getStudent().getLastName() +
                " - " + note.getLesson().getLessonName());
    }

    public void deleteNote(StudentNotesDTO note) {
        // some code here to delete note
        logger.debug("Note deleted: " + note.getStudent().getFirstName() + " " + note.getStudent().getLastName() +
                " - " + note.getLesson().getLessonName());
    }


}
