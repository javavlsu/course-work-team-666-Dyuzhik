package com.vlsu.ispi.services;

import com.vlsu.ispi.classes.BarberCost;
import com.vlsu.ispi.classes.StructTimeBarber;
import com.vlsu.ispi.classes.TimeBarber;
import com.vlsu.ispi.models.Record;
import com.vlsu.ispi.classes.Time;
import com.vlsu.ispi.models.Role;
import com.vlsu.ispi.models.User;
import com.vlsu.ispi.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    private final UserServiceImpl userServiceImpl;

    private final ServiceService serviceService;

    private final StatusService statusService;

    @Autowired
    public RecordService(RecordRepository recordRepository, UserServiceImpl userServiceImpl, ServiceService serviceService, StatusService statusService) {
        this.recordRepository = recordRepository;
        this.userServiceImpl = userServiceImpl;
        this.serviceService = serviceService;
        this.statusService = statusService;
    }

    public void save(Record record, int id, Date date) {
        User barber = userServiceImpl.findOne(record.getBarberID());
        com.vlsu.ispi.models.Service service = serviceService.findOne(id);
        User client = userServiceImpl.getCurrentAuthUser();
        record.setClient(client);
        record.setBarber(barber);
        record.setService(service);
        record.setDate(date);
        record.setStatus(statusService.findOne(1));

        boolean flag = false;
        for (Role role:barber.getRoles()) {
            if (role.getId() == 4) {
                flag = true;
                break;
            }
        }

        if (flag) record.setCost(service.getCost() + service.getCost()/5);
        else record.setCost(service.getCost());

        recordRepository.save(record);
    }

    public List<User> getFreeBarbers(int service) {
        return null;
    }

    public List<Date> getFreeDate(int service) throws ParseException {
        int countTimes = Time.values().length;
        int countBarbers = userServiceImpl.findAllBarbers().size();
        int maxCountRecordsForDate = countBarbers * countTimes;
        Calendar start = Calendar.getInstance();
        start.set(Calendar.HOUR, 0);
        List<Date> dates = new ArrayList<>();
        dates.add(start.getTime());
        for (int i = 1; i < 30; i++) {
            start.add(Calendar.DAY_OF_MONTH, 1);
            dates.add(start.getTime());
        }
        for (Date date : dates) {
            List<Record> records = recordRepository.findRecordByDate(date);
            if ((records.size() == maxCountRecordsForDate) && (getFreeTime(date, service).isEmpty()))
                dates.remove(date);
        }
        return dates;
    }

    public List<TimeBarber> getFreeTime(Date date, int service) throws ParseException {
        List<TimeBarber> freeTimes = new ArrayList<>();
        Time[] times = Time.values();
        List<Record> records = recordRepository.findRecordByDate(date);
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        List<User> barbers = userServiceImpl.findAllBarbers();
        List<BarberCost> costs = new ArrayList<>();
        int standartCost = serviceService.findOne(service).getCost();
        int newCost = standartCost + standartCost/5;
        for (User barber:barbers) {
            boolean flag = false;
            for (Role role: barber.getRoles()) {
                if (role.getId() == 4) {
                    flag = true;
                    break;
                }
            }
            if (flag) costs.add(new BarberCost(barber,newCost)); else costs.add(new BarberCost(barber,standartCost));
        }
        for (Time time : times) {
            java.sql.Time timeValue = new java.sql.Time(formatter.parse(time.getTime()).getTime());
            freeTimes.add(new TimeBarber(timeValue, new ArrayList<>(costs)));
            for (Record record : records) {
                if (formatter.format(record.getTime()).compareTo(time.getTime()) == 0) {
                    freeTimes.get(freeTimes.size() - 1).getBarbers().removeIf(barber -> barber.getBarber().getId() == record.getBarber().getId());
                }
            }
        }
        freeTimes.removeIf(time -> time.getBarbers().isEmpty());
        return freeTimes;
    }

}
