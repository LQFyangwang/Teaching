import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by xiao-kang on 2017/6/1.
 *
 * ObjectInputStream
 * ObjectOutputStream --> ByteArrayOutputStream    byte[]
 */
public class RedisTest {
      public static void main(String[] args) {
        JedisPool pool = new JedisPool(  "127.0.0.1", 6379);
        Jedis jedis = pool.getResource();
        jedis.set("c", "aaaaaa");
        System.out.println(jedis.get("a"));
        System.out.println(jedis.get("b"));
        System.out.println(jedis.get("c"));
        jedis.sadd("d", "ddd", "ddddd");
        System.out.println(jedis.smembers("d"));
        jedis.save();
    }
}
