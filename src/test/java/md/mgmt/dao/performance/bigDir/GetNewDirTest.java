package md.mgmt.dao.performance.bigDir;

import md.mgmt.BasePerformanceTest;
import md.mgmt.dao.FindRdbDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Mr-yang on 16-1-18.
 */
public class GetNewDirTest extends BasePerformanceTest {
    private static Logger logger = LoggerFactory.getLogger(GetNewDirTest.class);
    private static String methodDesc = "GetNewDirTest";
    @Autowired
    private FindRdbDao findRdbDao;

    public GetNewDirTest() {
        super(logger, methodDesc);
    }

    @Override
    public long execMethod(int hotCount, int count) {
        for (int i = 1; i < hotCount; i++) {
            findRdbDao.getNewDirMdIndex("key:" + i);
        }
        long start = System.currentTimeMillis();
        for (int i = 1; i < count; i++) {
            findRdbDao.getNewDirMdIndex("key:" + i);
        }
        long end = System.currentTimeMillis();
        logger.info(String.format("count %s  use Total time: %s ms, avg time: %sms",
                count, (end - start), (end - start) / (count * 1.0)));
        return end - start;
    }

    @Test
    public void testGetBigDirTest(){
        moduleMethod();
    }
}