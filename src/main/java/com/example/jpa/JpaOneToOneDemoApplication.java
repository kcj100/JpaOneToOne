package com.example.jpa;

import com.example.jpa.model.Type;
import com.example.jpa.model.Account;
import com.example.jpa.model.AccountProfile;
import com.example.jpa.repository.AccountRepository;
import com.example.jpa.repository.AccountProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Calendar;

@SpringBootApplication
public class JpaOneToOneDemoApplication implements CommandLineRunner {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountProfileRepository AccountProfileRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaOneToOneDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Clean up database tables
		AccountProfileRepository.deleteAllInBatch();
		accountRepository.deleteAllInBatch();

		//=========================================

		// Create a Account instance
		Account account = new Account("Rajeev", "Singh", "rajeev@callicoder.com",
				"MY_SUPER_SECRET_PASSWORD");

		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1992, 7, 21);

		// Create a AccountProfile instance
		AccountProfile AccountProfile = new AccountProfile("+91-8197882053", Type.CHECKING, dateOfBirth.getTime(),
				"747", "2nd Cross", "Golf View Road, Kodihalli", "Bangalore",
				"Karnataka", "India", "560008");


		// Set child reference(AccountProfile) in parent entity(account)
		account.setAccountProfile(AccountProfile);

		// Set parent reference(account) in child entity(AccountProfile)
		AccountProfile.setAccount(account);

		// Save Parent Reference (which will save the child as well)
		accountRepository.save(account);

		//=========================================
	}
}
