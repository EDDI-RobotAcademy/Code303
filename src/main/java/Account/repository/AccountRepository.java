package Account.repository;

import java.util.Optional;

import Account.entity.Account;

public interface AccountRepository {
	void save(Account accouunt);
	Optional<Account> findByUserId(String userId);
	Optional<Account> findById(Integer id);
}