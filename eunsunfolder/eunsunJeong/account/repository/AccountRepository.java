package CodeTest.eunsunJeong.account.repository;

import java.util.Optional;

import CodeTest.eunsunJeong.account.entity.Account;

public interface AccountRepository {
	int save(Account accouunt);
	Optional<Account> findByUserId(String userId);
	Optional<Account> findById(Integer id);
}