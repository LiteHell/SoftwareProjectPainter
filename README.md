# (대학 학부) 소프트웨어프로젝트 과목 과제
2020년 1학기 소프트웨어프로젝트 과목에 제출한 그림판 과제이다. 교수가 제시한 easyUML 플러그인이 NetBeans 구버전에서만 작동하는 관계로, NetBeans 8.2버전으로 작성했다. 또한 NetBeans 8.2버전은 최신버전의 JDK(프로젝트 작성당시 JDK 최신버전 : 14)를 쓰면 버그가 발생하는 관계로, JDK 8(8u231)을 설치해 실행했다.

과제에 어려운 학우들에게 도움이 됐으면 좋겠으나, 베끼지 않았으면 한다.

## 설명
수업에서 MVC 개념을 설명하고, MVC의 개념을 적용해 작성할 것을 요구했기에 Model, View, Controller를 나누어 작성되어 있다. `painter.Shape` 네임스페이스의 도형 클래스들과 그림 자체를 나타내는 `painter.Canvas` 클래스가 모델 클래스에 해당하고, `painter.PainterController` 클래스와 `painter.PainterView` 클래스가 각각 컨트롤러 클래스와 뷰 클래스에 해당된다. `painter.Shape` 내부의 도형 클래스들은 모두 `painter.Shape.Shape` 클래스를 상속한다. `painter.Shape.Shape` 클래스는 도형 구현에 필요한 여러가지 메소드들이 있으므로 참고하면 좋다.

이 그림판에서는 어떤 도형의 배경색을 바꾸거나, 이동하거나, 확대/축소하는 등의 (도형 추가, 삭제, 선택을 제외한 모든) 행위를 `Action`이라고 정의한다. 그리고 이 `Action`은 드래그를 해야 하는 `Action`과 드래그를 하지 않아도 되는 `Action`으로 나누어진다. 전자에 해당하는 `Action`들을 나타내는 클래스는 `painter.Action.DraggableAction`을 상속하고, 후자에 해당하는 `Action`들은 `painter.Action.DisposableAction`을 상속한다. 그리고 `painter.Action.DraggableAction`과 `painter.Action.DisposableAction`은 `painter.Action.Action`을 상속한다. 행위를 나타내는 모든 클래스들이 결과적으로 `painter.Action` 클래스를 상속하게 됨으로써 깔끔하고 객체지향적인 코드를 작성할 수 있었다.

`MouseEventProcessing` 클래스는 뷰에서 발생하는 마우스 이벤트를 처리하기 위한 클래스다. 원래는 컨트롤러 클래스에 마우스 이벤트 처리 코드도 같이 넣었었는데, 수업에서 마우스 이벤트를 처리하는 별도의 클래스를 만드는 식으로 설명하길래 바로 설계를 변경했다.

`PaintablePanel`은 `Shape` 클래스들의 컬렉션을 받아 그리는 패널로, 선택된 도형을 설정할 수 있다.(선택된 도형이 없을 시에는 선택된 도형을 `null`로 설정한다) 선택된 도형은 도형 주변에 사각형 형태의 움직이는 점선을 그리게 했는데, 이 점선에 움직이는 효과를 주기 위해 내부 타이머로 `repaint` 메소드를 주기적으로 호출해 다시 렌더링하도록 했다. 또한 이 `PaintablePanel` 클래스는 커서가 변경가능하다는 것을 나타내기 위해 명시적으로 `painter.CursorChangable` 인터페이스를 상속하도록 했다. 이 `CursorChangable` 인터페이스는 `Action` 클래스 인스턴스에서 커서를 바꿀 때 이용된다.

프로젝트를 구현하면서 나름 최대한 객체지향적으로 작성하고자 했다. 더 많은 시간이 있었다면 더 깔끔하고 아름다운 코드를 볼 수 있지 않았을까 싶다.

이 프로젝트의 메인 클래스는 `painter.Painter`다.