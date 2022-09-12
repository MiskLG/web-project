package service;

import beans.PromoCode;
import repository.PromoCodeRepository;

import java.util.ArrayList;
import java.util.Calendar;

public class PromoCodeService {
    private PromoCodeRepository repository;

    public PromoCodeService() {
        repository = PromoCodeRepository.init();
    }

    public void add(PromoCode code) {
        if(this.getById(code.getId()) == null) {
            repository.add(code);
        }
    }

    public ArrayList<PromoCode> getAll() {
        return repository.getAll();
    }

    public void use(String id) {
        PromoCode code = this.getById(id);
        code.setNumberOfUsage(code.getNumberOfUsage() - 1);
        repository.update(code);
    }

    public PromoCode getById(String id) {
        ArrayList<PromoCode> codes = this.getAll();
        if (codes == null) {
            return null;
        }
        for (PromoCode code: codes) {
            if (code.getId().equalsIgnoreCase(id)) {
                if(code.getNumberOfUsage() <= 0) {
                    break;
                }
                if (Calendar.getInstance().getTime().compareTo(code.getStartTime()) > 0 && Calendar.getInstance().getTime().compareTo(code.getEndTime()) < 0) {
                    return code;
                }
                else {
                    break;
                }
            }
        }
        return null;
    }
}
