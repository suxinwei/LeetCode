package test.bean;

/**
 * created by 80288791 at 2020/6/22
 * description: SD卡文件信息
 */
public class PlayBackFileInfo {
    public String recordFileName;
    public String thumbnailFileName;
    public long recordStartTime;    // 录制开始时
    public long recordEndTime;    // 录制结束时
    public int recordElapsedTime;   // 录制时长

    @Override
    public String toString() {
        return "PlayBackFileInfo{" +
                "recordFileName='" + recordFileName + '\'' +
                ", thumbnailFileName='" + thumbnailFileName + '\'' +
                ", recordStartTime=" + recordStartTime +
                ", recordEndTime=" + recordEndTime +
                ", recordElapsedTime=" + recordElapsedTime +
                '}';
    }
}
