# Первая Лабараторная работа по курсу ППвИС

# Тема:"РАЗРАБОТКА ИНТЕРАКТИВНОГО ПРИЛОЖЕНИЯ С ПРИМЕНЕНИЕМ ОСНОВНЫХ КОМПОНЕНТОВ ДЛЯ ПОСТРОЕНИЯ ПОЛЬЗОВАТЕЛЬСКОГО ИНТЕРФЕЙСА"


Козлов К.А-821701
Вариант19

(19. Реализовать две горячие комбинации клавиш. Например, ctrl+R,
ctrl+S. При нажатии на первую комбинацию(ctrl+R): основное окно
приложения, разбивается на 5 окон в каждом из которых расположено по
одной группе элементов. Через 1 секунду 5 новых окон размещаются по
кругу на экране. Затем 5 окон переставляются по очереди между собой по
часовой стрелке с задержкой в 1 секунду. После окончания перестановки
через 1 секунду на экране снова появляется основное окно, затем описанный
процесс повторяется вновь. При нажатии второй комбинации клавиш(ctrl+S)
описанный процесс останавливается в любой момент времени. Если
повторно нажать вторую комбинацию (ctrl+R), то процесс продолжится с
того места, где был остановлен.)


# Методы лабараторной работы:


shellSwap-метод для перемещения окон по кругу 


addShell-метод добавления и размещения нового shellа в display


setCircle-размещение шеллов по кругу


openShells-открытие шеллов занесенных в массив


oneSecondPassCheck-метод отсчитывающий секунду
