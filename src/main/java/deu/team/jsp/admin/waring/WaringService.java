package deu.team.jsp.admin.waring;

import deu.team.jsp.account.AccountRepository;
import deu.team.jsp.account.domain.Account;
import deu.team.jsp.account.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class WaringService {

    private final WaringRepository waringRepository;
    private final AccountRepository accountRepository;

    public List<Account> getAllStudentList(){
        return accountRepository.findAllByRole(Role.STUDENT);
    }

    public void giveWaring(String studentId){

        Optional<Waring> target = waringRepository.findByStudentId(studentId);
        if (target.isPresent()) {
            int totalWaringCnt = target.get().getWaringCnt() + 1;
            if (totalWaringCnt <= 3) {
                target.get().changeWaringCnt(totalWaringCnt);
                waringRepository.save(target.get());
            }
        }else{
            accountRepository.getAccountByStudentId(studentId).ifPresent(
                    targetAccount ->{
                        Waring targetWaring = Waring.builder().studentId(studentId).build();
                        waringRepository.save(targetWaring);
                        targetAccount.setWaring(targetWaring);
                        accountRepository.save(targetAccount);
                    }
            );
        }
    }

    public void resetWaringCnt(String studentId){
        Optional<Waring> targetStudent = waringRepository.findByStudentId(studentId);
        targetStudent.ifPresent(target->{
            target.changeWaringCnt(0);
            waringRepository.save(target);
        });
    }
}


