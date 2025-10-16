package bd.edu.seu.lendengo.interfaces;

import bd.edu.seu.lendengo.models.Notice;

import java.util.ArrayList;

public interface NoticeInterface {
    public int insertNotice(Notice notice);
    public int updateNotice(Notice notice);
    public Notice getActiveNotice();
    public ArrayList<Notice> getAllNotices();
}
