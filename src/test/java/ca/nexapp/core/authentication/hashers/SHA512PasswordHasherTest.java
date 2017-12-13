package ca.nexapp.core.authentication.hashers;

public class SHA512PasswordHasherTest extends PasswordHasherTest {

    @Override
    protected PasswordHasher createHasher() {
        return new SHA512PasswordHasher();
    }

}
