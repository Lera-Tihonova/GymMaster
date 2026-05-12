import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== GYM MASTER - Управление тренировками ===\n");

        Timetable timetable = new Timetable();

        Group childGroup = new Group("Акробатика для детей 7-10 лет", Age.CHILD, 60);
        Group teenGroup = new Group("Спортивная гимнастика 11-15 лет", Age.CHILD, 90);
        Group adultGroup = new Group("Йога для взрослых", Age.ADULT, 75);
        Group fitnessGroup = new Group("Фитнес для взрослых", Age.ADULT, 60);

        // Создаем тренеров
        Coach coach1 = new Coach("Иванов", "Иван", "Иванович");
        Coach coach2 = new Coach("Петрова", "Мария", "Сергеевна");
        Coach coach3 = new Coach("Сидоров", "Алексей", "Владимирович");
        Coach coach4 = new Coach("Кузнецова", "Елена", "Андреевна");

        System.out.println("Добавляем тренировки в расписание...");

        timetable.addNewTrainingSession(new TrainingSession(childGroup, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(adultGroup, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(12, 0)));
        timetable.addNewTrainingSession(new TrainingSession(fitnessGroup, coach4,
                DayOfWeek.MONDAY, new TimeOfDay(18, 0)));

        timetable.addNewTrainingSession(new TrainingSession(teenGroup, coach3,
                DayOfWeek.TUESDAY, new TimeOfDay(15, 0)));
        timetable.addNewTrainingSession(new TrainingSession(adultGroup, coach2,
                DayOfWeek.TUESDAY, new TimeOfDay(19, 0)));

        timetable.addNewTrainingSession(new TrainingSession(childGroup, coach1,
                DayOfWeek.WEDNESDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(fitnessGroup, coach4,
                DayOfWeek.WEDNESDAY, new TimeOfDay(20, 0)));

        timetable.addNewTrainingSession(new TrainingSession(teenGroup, coach3,
                DayOfWeek.THURSDAY, new TimeOfDay(15, 0)));

        timetable.addNewTrainingSession(new TrainingSession(adultGroup, coach2,
                DayOfWeek.FRIDAY, new TimeOfDay(18, 0)));
        timetable.addNewTrainingSession(new TrainingSession(childGroup, coach1,
                DayOfWeek.FRIDAY, new TimeOfDay(10, 0)));

        timetable.addNewTrainingSession(new TrainingSession(fitnessGroup, coach4,
                DayOfWeek.SATURDAY, new TimeOfDay(11, 0)));
        timetable.addNewTrainingSession(new TrainingSession(teenGroup, coach3,
                DayOfWeek.SATURDAY, new TimeOfDay(14, 0)));

        timetable.addNewTrainingSession(new TrainingSession(adultGroup, coach2,
                DayOfWeek.SUNDAY, new TimeOfDay(12, 0)));

        System.out.println("Готово! Добавлено 13 тренировок\n");

        System.out.println("=== РАСПИСАНИЕ НА НЕДЕЛЮ ===\n");

        DayOfWeek[] days = {
                DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY
        };

        String[] dayNames = {
                "ПОНЕДЕЛЬНИК", "ВТОРНИК", "СРЕДА",
                "ЧЕТВЕРГ", "ПЯТНИЦА", "СУББОТА", "ВОСКРЕСЕНЬЕ"
        };

        for (int i = 0; i < days.length; i++) {
            System.out.println("📅 " + dayNames[i] + ":");
            List<TrainingSession> sessions = timetable.getTrainingSessionsForDay(days[i]);

            if (sessions.isEmpty()) {
                System.out.println("   Нет тренировок");
            } else {
                for (TrainingSession session : sessions) {
                    System.out.printf("   %02d:%02d - %s (тренер: %s)%n",
                            session.getTimeOfDay().getHours(),
                            session.getTimeOfDay().getMinutes(),
                            session.getGroup().getTitle(),
                            session.getCoach().getFullName()
                    );
                }
            }
            System.out.println();
        }

        System.out.println("=== ПОИСК ТРЕНИРОВОК ===\n");

        TimeOfDay searchTime = new TimeOfDay(10, 0);
        List<TrainingSession> foundSessions = timetable.getTrainingSessionsForDayAndTime(
                DayOfWeek.MONDAY, searchTime);

        System.out.println("Тренировки в понедельник в " + searchTime + ":");
        if (foundSessions.isEmpty()) {
            System.out.println("   Не найдено");
        } else {
            for (TrainingSession session : foundSessions) {
                System.out.println("   ✅ " + session.getGroup().getTitle() +
                        " - " + session.getCoach().getFullName());
            }
        }

        System.out.println();

        System.out.println("=== СТАТИСТИКА ТРЕНЕРОВ ===\n");

        List<CounterOfTrainings> coachStats = timetable.getCountByCoaches();

        System.out.println("Количество тренировок в неделю (по убыванию):");
        int place = 1;
        for (CounterOfTrainings stat : coachStats) {
            System.out.printf("   %d. %s: %d тренировки(ок)%n",
                    place++,
                    stat.getCoach().getFullName(),
                    stat.getCount()
            );
        }

        System.out.println("\n=== ПРОГРАММА ЗАВЕРШЕНА ===");
    }
}