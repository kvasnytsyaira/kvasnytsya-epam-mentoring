import java.util.*;

public class Main {
    public static void main(String[] args) {
//        int[] arr = new int[Integer.MAX_VALUE];
//        List<Integer> list = new ArrayList<>(20);
//        List<Integer> list2 = new LinkedList<Integer>();

        HashMap<User, Integer> objectObjectHashMap = new HashMap<>();
        User user = new User(1);
        objectObjectHashMap.put(user, 1);
        System.out.println(objectObjectHashMap.get(user));
        user.setAge(2);
        System.out.println(objectObjectHashMap.get(user));

//        objectObjectHashMap.put(1, 2);
//        System.out.println(objectObjectHashMap.get(1));
    }

    static class User {
        int age;

        public User(int age) {
            this.age = age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public int hashCode() {
            return age;
        }
    }
}
