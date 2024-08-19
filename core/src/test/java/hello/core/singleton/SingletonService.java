package hello.core.singleton;

public class SingletonService {

    //1. static 영역에 객체를 미리 1개만 생성해둔다.
    // 자기 자신을 내부의 프라이빗/스태틱으로 하나 가지고 있음. -> 클래스 레벨에 올라가기때문에 딱 하나만 존재 가능
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    /**
     이 객체 인스턴스가 필요하면 오직 `getInstance()` 메서드를 통해서만 조회할 수 있다. 이 메서드를 호
     출하면 항상 같은 인스턴스를 반환한다.
     **/
    public static SingletonService getInstance() {
         return instance;
    }
    // jvm이 뜰 때, SingletonService의 static 영역을 내부적으로 실행하여 초기화 한 다음, 자기 자신을 생성하여 instance에 참조함.
    // 자기 자신을 instance 객체 하나 생성

    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    // private 생성자 (다른 클래스에서 새로운 SingletonService 객체를 만드는 것을 방지해줌.
    /**
     딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드
     로 객체 인스턴스가 생성되는 것을 막는다.
     **/
    private SingletonService() {}

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
    public static void main(String[] args) {
//        SingletonService service1 = SingletonService.getInstance();
    }
}
