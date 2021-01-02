/*
 * package com.banking.credit.credit;
 * 
 * import static org.junit.Assert.assertNotNull;
 * 
 * import java.util.HashMap;
 * 
 * import org.apache.http.util.Asserts; import org.junit.Before; import
 * org.junit.Test; import org.junit.jupiter.api.extension.ExtendWith; import
 * org.junit.runner.RunWith; import org.mockito.InjectMocks; import
 * org.mockito.Mock; import org.mockito.*; import
 * org.mockito.MockitoAnnotations; import
 * org.mockito.junit.jupiter.MockitoExtension; import
 * org.mockito.runners.MockitoJUnitRunner; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.boot.test.context.SpringBootTest.WebEnvironment; import
 * org.springframework.test.context.junit.jupiter.SpringExtension; import
 * org.springframework.test.context.junit4.SpringRunner;
 * 
 * import com.banking.transaction.entities.BankAccount; import
 * com.banking.transaction.repository.BankAccountRepository; import
 * com.banking.transaction.serviceimpl.TransactionServiceImpl;
 * 
 * 
 * @RunWith(SpringRunner.class) public class TransactionUnitTest {
 * 
 * 
 * @Mock private BankAccountRepository bankRepo;
 * 
 * @Before public void setUp() { BankAccount account = new BankAccount();
 * 
 * Mockito.when(bankRepo.save(account)) .thenReturn(account); }
 * 
 * @Mock private TransactionServiceImpl transactionService;
 * 
 * 
 * @Test public void testUser() {
 * assertNotNull(transactionService.financeTransactions(new HashMap<String,
 * Object>())); } }
 */