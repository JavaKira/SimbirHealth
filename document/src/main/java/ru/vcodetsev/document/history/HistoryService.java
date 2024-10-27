package ru.vcodetsev.document.history;

import java.time.LocalDateTime;
import java.util.Collection;

public interface HistoryService {
    Collection<Long> getAllByAccount(long accountId);

    VisitHistoryDto getInfoById(long historyId);

    VisitHistory visitHistory(long historyId);

    VisitHistoryDto create(LocalDateTime date, long patientId, long hospitalId, long doctorId, String room, String data);

    VisitHistoryDto update(long historyId, LocalDateTime date, long patientId, long hospitalId, long doctorId, String room, String data);
}
