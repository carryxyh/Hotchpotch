/**
 * ThrowUtils
 * 利用泛型骗过编译器
 * 利用泛型擦除，让编译器在编译阶段不产生checkcast指令，所以不会产生classcastexception
 *
 * @author xiuyuhang [xiuyuhang]
 */
public class ThrowUtils {

    public static void throwException(Throwable t) {
        if (t == null) {
            throw new NullPointerException("the throwable can not be null!");
        }
        ThrowUtils.<RuntimeException>throwException0(t);
    }

    @SuppressWarnings("unchecked")
    private static <E extends Throwable> void throwException0(Throwable t) throws E {
        throw (E) t;
    }
}