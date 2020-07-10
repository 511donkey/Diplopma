import java.util.HashMap;
import java.util.Map;

public class Controllers {
    // отдельный класс, в котором будут собираться контроллеры.
        private static final Map<Class<?>, Object> controllers = new HashMap<>();

        public static void register(Object object) {
            if (object == null) return;
            controllers.put(object.getClass(), object);
        }

        public static Object get(Class<?> aClass) {
            return aClass == null ? null : controllers.get(aClass);
        }
    }

    // базовый класс, от которого нужно наследовать все контроллеры
    abstract class BasicController {
        public BasicController() {
            Controllers.register(this);
        }
    }

    // один контроллер
    class FirstPageController extends BasicController {
// какой-то там исходный код работы контроллера
// с использованием @FXML и так далее.

// за счёт extends BasicController
// каждый контроллер автоматически добавляется в мапу controllers
// (при создании объекта == при вызове конструктора родителя).
    }

    // другой контроллер
    class SecondPageController extends BasicController {
// исходный код работы второго контроллера.

        // когда нужно обратиться к другому контроллеру:
// определяем какой именно контроллер нужен (какой класс использовать) -
// и используем его как аргумент для get() и как приведение типов.
        FirstPageController neededController = (FirstPageController) Controllers.get(FirstPageController.class);
// приведение типов нужно использовать, потому что get() в общем случае возвращает Object.
    }

