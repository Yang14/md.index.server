package md.mgmt.service.performance;

import md.mgmt.BasePerformanceTest;
import md.mgmt.base.md.MdIndex;
import md.mgmt.service.FindMdIndexService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Mr-yang on 16-1-18.
 */
public class TestFindFileMdIndexService extends BasePerformanceTest{
    private static Logger logger = LoggerFactory.getLogger(TestFindFileMdIndexService.class);
    private static String methodDesc = "testFindFileMdIndex";

    @Autowired
    private FindMdIndexService findMdIndexService;

    public TestFindFileMdIndexService() {
        super(logger, methodDesc);
    }

    @Override
    public long execMethod(int hotCount, int count) {
        String path = "/home/a";
        for (int i = 1; i < hotCount; i++) {
            findMdIndexService.findFileMdIndex(new MdIndex(path, "testFile" + i));
        }
        long start = System.currentTimeMillis();
        for (int i = 1; i < count; i++) {
            findMdIndexService.findFileMdIndex(new MdIndex(path, "testFile" + i));
        }
        long end = System.currentTimeMillis();
        logger.info(String.format("count: %s  use Total time: %s ms, avg time: %sms",
                count, (end - start), (end - start) / (count * 1.0)));
        return end - start;
    }

    @Test
    public void testFindFileMdIndex(){
        moduleMethod();
    }
}
