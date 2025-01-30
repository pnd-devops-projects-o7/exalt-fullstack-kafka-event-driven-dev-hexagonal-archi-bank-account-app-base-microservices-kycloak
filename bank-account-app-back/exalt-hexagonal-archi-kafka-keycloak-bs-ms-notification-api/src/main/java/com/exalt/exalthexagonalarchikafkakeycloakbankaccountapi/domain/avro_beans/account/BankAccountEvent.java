/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class BankAccountEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4809680341918844335L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BankAccountEvent\",\"namespace\":\"com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account\",\"fields\":[{\"name\":\"status\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"message\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"accountType\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"bankAccount\",\"type\":{\"type\":\"record\",\"name\":\"BankAccount\",\"fields\":[{\"name\":\"accountId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"type\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"accountState\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"balance\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\",\"java-class\":\"java.math.BigDecimal\",\"precision\":7,\"scale\":3}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}},{\"name\":\"overdraft\",\"type\":\"double\"},{\"name\":\"interestRate\",\"type\":\"double\"},{\"name\":\"customer\",\"type\":{\"type\":\"record\",\"name\":\"Customer\",\"fields\":[{\"name\":\"customerId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"firstname\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"lastname\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"email\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"customerState\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}}]}}]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
  }

  private static final BinaryMessageEncoder<BankAccountEvent> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<BankAccountEvent> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<BankAccountEvent> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<BankAccountEvent> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<BankAccountEvent> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this BankAccountEvent to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a BankAccountEvent from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a BankAccountEvent instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static BankAccountEvent fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String status;
  private java.lang.String message;
  private java.lang.String accountType;
  private com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount bankAccount;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public BankAccountEvent() {}

  /**
   * All-args constructor.
   * @param status The new value for status
   * @param message The new value for message
   * @param accountType The new value for accountType
   * @param bankAccount The new value for bankAccount
   */
  public BankAccountEvent(java.lang.String status, java.lang.String message, java.lang.String accountType, com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount bankAccount) {
    this.status = status;
    this.message = message;
    this.accountType = accountType;
    this.bankAccount = bankAccount;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return status;
    case 1: return message;
    case 2: return accountType;
    case 3: return bankAccount;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: status = value$ != null ? value$.toString() : null; break;
    case 1: message = value$ != null ? value$.toString() : null; break;
    case 2: accountType = value$ != null ? value$.toString() : null; break;
    case 3: bankAccount = (com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'status' field.
   * @return The value of the 'status' field.
   */
  public java.lang.String getStatus() {
    return status;
  }


  /**
   * Sets the value of the 'status' field.
   * @param value the value to set.
   */
  public void setStatus(java.lang.String value) {
    this.status = value;
  }

  /**
   * Gets the value of the 'message' field.
   * @return The value of the 'message' field.
   */
  public java.lang.String getMessage() {
    return message;
  }


  /**
   * Sets the value of the 'message' field.
   * @param value the value to set.
   */
  public void setMessage(java.lang.String value) {
    this.message = value;
  }

  /**
   * Gets the value of the 'accountType' field.
   * @return The value of the 'accountType' field.
   */
  public java.lang.String getAccountType() {
    return accountType;
  }


  /**
   * Sets the value of the 'accountType' field.
   * @param value the value to set.
   */
  public void setAccountType(java.lang.String value) {
    this.accountType = value;
  }

  /**
   * Gets the value of the 'bankAccount' field.
   * @return The value of the 'bankAccount' field.
   */
  public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount getBankAccount() {
    return bankAccount;
  }


  /**
   * Sets the value of the 'bankAccount' field.
   * @param value the value to set.
   */
  public void setBankAccount(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount value) {
    this.bankAccount = value;
  }

  /**
   * Creates a new BankAccountEvent RecordBuilder.
   * @return A new BankAccountEvent RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder newBuilder() {
    return new com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder();
  }

  /**
   * Creates a new BankAccountEvent RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new BankAccountEvent RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder newBuilder(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder other) {
    if (other == null) {
      return new com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder();
    } else {
      return new com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder(other);
    }
  }

  /**
   * Creates a new BankAccountEvent RecordBuilder by copying an existing BankAccountEvent instance.
   * @param other The existing instance to copy.
   * @return A new BankAccountEvent RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder newBuilder(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent other) {
    if (other == null) {
      return new com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder();
    } else {
      return new com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder(other);
    }
  }

  /**
   * RecordBuilder for BankAccountEvent instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<BankAccountEvent>
    implements org.apache.avro.data.RecordBuilder<BankAccountEvent> {

    private java.lang.String status;
    private java.lang.String message;
    private java.lang.String accountType;
    private com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount bankAccount;
    private com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount.Builder bankAccountBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.status)) {
        this.status = data().deepCopy(fields()[0].schema(), other.status);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.message)) {
        this.message = data().deepCopy(fields()[1].schema(), other.message);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.accountType)) {
        this.accountType = data().deepCopy(fields()[2].schema(), other.accountType);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.bankAccount)) {
        this.bankAccount = data().deepCopy(fields()[3].schema(), other.bankAccount);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (other.hasBankAccountBuilder()) {
        this.bankAccountBuilder = com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount.newBuilder(other.getBankAccountBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing BankAccountEvent instance
     * @param other The existing instance to copy.
     */
    private Builder(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.status)) {
        this.status = data().deepCopy(fields()[0].schema(), other.status);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.message)) {
        this.message = data().deepCopy(fields()[1].schema(), other.message);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.accountType)) {
        this.accountType = data().deepCopy(fields()[2].schema(), other.accountType);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.bankAccount)) {
        this.bankAccount = data().deepCopy(fields()[3].schema(), other.bankAccount);
        fieldSetFlags()[3] = true;
      }
      this.bankAccountBuilder = null;
    }

    /**
      * Gets the value of the 'status' field.
      * @return The value.
      */
    public java.lang.String getStatus() {
      return status;
    }


    /**
      * Sets the value of the 'status' field.
      * @param value The value of 'status'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder setStatus(java.lang.String value) {
      validate(fields()[0], value);
      this.status = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'status' field has been set.
      * @return True if the 'status' field has been set, false otherwise.
      */
    public boolean hasStatus() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'status' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder clearStatus() {
      status = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'message' field.
      * @return The value.
      */
    public java.lang.String getMessage() {
      return message;
    }


    /**
      * Sets the value of the 'message' field.
      * @param value The value of 'message'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder setMessage(java.lang.String value) {
      validate(fields()[1], value);
      this.message = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'message' field has been set.
      * @return True if the 'message' field has been set, false otherwise.
      */
    public boolean hasMessage() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'message' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder clearMessage() {
      message = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'accountType' field.
      * @return The value.
      */
    public java.lang.String getAccountType() {
      return accountType;
    }


    /**
      * Sets the value of the 'accountType' field.
      * @param value The value of 'accountType'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder setAccountType(java.lang.String value) {
      validate(fields()[2], value);
      this.accountType = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'accountType' field has been set.
      * @return True if the 'accountType' field has been set, false otherwise.
      */
    public boolean hasAccountType() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'accountType' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder clearAccountType() {
      accountType = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'bankAccount' field.
      * @return The value.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount getBankAccount() {
      return bankAccount;
    }


    /**
      * Sets the value of the 'bankAccount' field.
      * @param value The value of 'bankAccount'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder setBankAccount(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount value) {
      validate(fields()[3], value);
      this.bankAccountBuilder = null;
      this.bankAccount = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'bankAccount' field has been set.
      * @return True if the 'bankAccount' field has been set, false otherwise.
      */
    public boolean hasBankAccount() {
      return fieldSetFlags()[3];
    }

    /**
     * Gets the Builder instance for the 'bankAccount' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount.Builder getBankAccountBuilder() {
      if (bankAccountBuilder == null) {
        if (hasBankAccount()) {
          setBankAccountBuilder(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount.newBuilder(bankAccount));
        } else {
          setBankAccountBuilder(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount.newBuilder());
        }
      }
      return bankAccountBuilder;
    }

    /**
     * Sets the Builder instance for the 'bankAccount' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder setBankAccountBuilder(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount.Builder value) {
      clearBankAccount();
      bankAccountBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'bankAccount' field has an active Builder instance
     * @return True if the 'bankAccount' field has an active Builder instance
     */
    public boolean hasBankAccountBuilder() {
      return bankAccountBuilder != null;
    }

    /**
      * Clears the value of the 'bankAccount' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent.Builder clearBankAccount() {
      bankAccount = null;
      bankAccountBuilder = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BankAccountEvent build() {
      try {
        BankAccountEvent record = new BankAccountEvent();
        record.status = fieldSetFlags()[0] ? this.status : (java.lang.String) defaultValue(fields()[0]);
        record.message = fieldSetFlags()[1] ? this.message : (java.lang.String) defaultValue(fields()[1]);
        record.accountType = fieldSetFlags()[2] ? this.accountType : (java.lang.String) defaultValue(fields()[2]);
        if (bankAccountBuilder != null) {
          try {
            record.bankAccount = this.bankAccountBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("bankAccount"));
            throw e;
          }
        } else {
          record.bankAccount = fieldSetFlags()[3] ? this.bankAccount : (com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount) defaultValue(fields()[3]);
        }
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<BankAccountEvent>
    WRITER$ = (org.apache.avro.io.DatumWriter<BankAccountEvent>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<BankAccountEvent>
    READER$ = (org.apache.avro.io.DatumReader<BankAccountEvent>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










