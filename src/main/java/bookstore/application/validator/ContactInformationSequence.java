package bookstore.application.validator;

import jakarta.validation.GroupSequence;

@GroupSequence({InformationValidator.class, ContactInformationValidator.class})
public interface ContactInformationSequence {
}
