/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class BankAccount extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 3451044446703772645L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BankAccount\",\"namespace\":\"com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies\",\"fields\":[{\"name\":\"accountId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"type\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"accountState\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"balance\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\",\"java-class\":\"java.math.BigDecimal\",\"precision\":7,\"scale\":3}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}},{\"name\":\"overdraft\",\"type\":\"double\"},{\"name\":\"interestRate\",\"type\":\"double\"},{\"name\":\"customer\",\"type\":{\"type\":\"record\",\"name\":\"Customer\",\"fields\":[{\"name\":\"customerId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"firstname\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"lastname\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"email\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"customerState\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}}]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
  }

  private static final BinaryMessageEncoder<BankAccount> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<BankAccount> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<BankAccount> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<BankAccount> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<BankAccount> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this BankAccount to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a BankAccount from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a BankAccount instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static BankAccount fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String accountId;
  private java.lang.String type;
  private java.lang.String accountState;
  private java.math.BigDecimal balance;
  private java.time.Instant createdAt;
  private double overdraft;
  private double interestRate;
  private com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer customer;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public BankAccount() {}

  /**
   * All-args constructor.
   * @param accountId The new value for accountId
   * @param type The new value for type
   * @param accountState The new value for accountState
   * @param balance The new value for balance
   * @param createdAt The new value for createdAt
   * @param overdraft The new value for overdraft
   * @param interestRate The new value for interestRate
   * @param customer The new value for customer
   */
  public BankAccount(java.lang.String accountId, java.lang.String type, java.lang.String accountState, java.math.BigDecimal balance, java.time.Instant createdAt, java.lang.Double overdraft, java.lang.Double interestRate, com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer customer) {
    this.accountId = accountId;
    this.type = type;
    this.accountState = accountState;
    this.balance = balance;
    this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
    this.overdraft = overdraft;
    this.interestRate = interestRate;
    this.customer = customer;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return accountId;
    case 1: return type;
    case 2: return accountState;
    case 3: return balance;
    case 4: return createdAt;
    case 5: return overdraft;
    case 6: return interestRate;
    case 7: return customer;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      null,
      null,
      null,
      null,
      new org.apache.avro.data.TimeConversions.TimestampMillisConversion(),
      null,
      null,
      null,
      null
  };

  @Override
  public org.apache.avro.Conversion<?> getConversion(int field) {
    return conversions[field];
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: accountId = value$ != null ? value$.toString() : null; break;
    case 1: type = value$ != null ? value$.toString() : null; break;
    case 2: accountState = value$ != null ? value$.toString() : null; break;
    case 3: balance = (java.math.BigDecimal)value$; break;
    case 4: createdAt = (java.time.Instant)value$; break;
    case 5: overdraft = (java.lang.Double)value$; break;
    case 6: interestRate = (java.lang.Double)value$; break;
    case 7: customer = (com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'accountId' field.
   * @return The value of the 'accountId' field.
   */
  public java.lang.String getAccountId() {
    return accountId;
  }


  /**
   * Sets the value of the 'accountId' field.
   * @param value the value to set.
   */
  public void setAccountId(java.lang.String value) {
    this.accountId = value;
  }

  /**
   * Gets the value of the 'type' field.
   * @return The value of the 'type' field.
   */
  public java.lang.String getType() {
    return type;
  }


  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public void setType(java.lang.String value) {
    this.type = value;
  }

  /**
   * Gets the value of the 'accountState' field.
   * @return The value of the 'accountState' field.
   */
  public java.lang.String getAccountState() {
    return accountState;
  }


  /**
   * Sets the value of the 'accountState' field.
   * @param value the value to set.
   */
  public void setAccountState(java.lang.String value) {
    this.accountState = value;
  }

  /**
   * Gets the value of the 'balance' field.
   * @return The value of the 'balance' field.
   */
  public java.math.BigDecimal getBalance() {
    return balance;
  }


  /**
   * Sets the value of the 'balance' field.
   * @param value the value to set.
   */
  public void setBalance(java.math.BigDecimal value) {
    this.balance = value;
  }

  /**
   * Gets the value of the 'createdAt' field.
   * @return The value of the 'createdAt' field.
   */
  public java.time.Instant getCreatedAt() {
    return createdAt;
  }


  /**
   * Sets the value of the 'createdAt' field.
   * @param value the value to set.
   */
  public void setCreatedAt(java.time.Instant value) {
    this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
  }

  /**
   * Gets the value of the 'overdraft' field.
   * @return The value of the 'overdraft' field.
   */
  public double getOverdraft() {
    return overdraft;
  }


  /**
   * Sets the value of the 'overdraft' field.
   * @param value the value to set.
   */
  public void setOverdraft(double value) {
    this.overdraft = value;
  }

  /**
   * Gets the value of the 'interestRate' field.
   * @return The value of the 'interestRate' field.
   */
  public double getInterestRate() {
    return interestRate;
  }


  /**
   * Sets the value of the 'interestRate' field.
   * @param value the value to set.
   */
  public void setInterestRate(double value) {
    this.interestRate = value;
  }

  /**
   * Gets the value of the 'customer' field.
   * @return The value of the 'customer' field.
   */
  public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer getCustomer() {
    return customer;
  }


  /**
   * Sets the value of the 'customer' field.
   * @param value the value to set.
   */
  public void setCustomer(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer value) {
    this.customer = value;
  }

  /**
   * Creates a new BankAccount RecordBuilder.
   * @return A new BankAccount RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder newBuilder() {
    return new com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder();
  }

  /**
   * Creates a new BankAccount RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new BankAccount RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder newBuilder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder other) {
    if (other == null) {
      return new com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder();
    } else {
      return new com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder(other);
    }
  }

  /**
   * Creates a new BankAccount RecordBuilder by copying an existing BankAccount instance.
   * @param other The existing instance to copy.
   * @return A new BankAccount RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder newBuilder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount other) {
    if (other == null) {
      return new com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder();
    } else {
      return new com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder(other);
    }
  }

  /**
   * RecordBuilder for BankAccount instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<BankAccount>
    implements org.apache.avro.data.RecordBuilder<BankAccount> {

    private java.lang.String accountId;
    private java.lang.String type;
    private java.lang.String accountState;
    private java.math.BigDecimal balance;
    private java.time.Instant createdAt;
    private double overdraft;
    private double interestRate;
    private com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer customer;
    private com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer.Builder customerBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.accountId)) {
        this.accountId = data().deepCopy(fields()[0].schema(), other.accountId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.accountState)) {
        this.accountState = data().deepCopy(fields()[2].schema(), other.accountState);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.balance)) {
        this.balance = data().deepCopy(fields()[3].schema(), other.balance);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[4].schema(), other.createdAt);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.overdraft)) {
        this.overdraft = data().deepCopy(fields()[5].schema(), other.overdraft);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.interestRate)) {
        this.interestRate = data().deepCopy(fields()[6].schema(), other.interestRate);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.customer)) {
        this.customer = data().deepCopy(fields()[7].schema(), other.customer);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
      if (other.hasCustomerBuilder()) {
        this.customerBuilder = com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer.newBuilder(other.getCustomerBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing BankAccount instance
     * @param other The existing instance to copy.
     */
    private Builder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.accountId)) {
        this.accountId = data().deepCopy(fields()[0].schema(), other.accountId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.accountState)) {
        this.accountState = data().deepCopy(fields()[2].schema(), other.accountState);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.balance)) {
        this.balance = data().deepCopy(fields()[3].schema(), other.balance);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[4].schema(), other.createdAt);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.overdraft)) {
        this.overdraft = data().deepCopy(fields()[5].schema(), other.overdraft);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.interestRate)) {
        this.interestRate = data().deepCopy(fields()[6].schema(), other.interestRate);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.customer)) {
        this.customer = data().deepCopy(fields()[7].schema(), other.customer);
        fieldSetFlags()[7] = true;
      }
      this.customerBuilder = null;
    }

    /**
      * Gets the value of the 'accountId' field.
      * @return The value.
      */
    public java.lang.String getAccountId() {
      return accountId;
    }


    /**
      * Sets the value of the 'accountId' field.
      * @param value The value of 'accountId'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder setAccountId(java.lang.String value) {
      validate(fields()[0], value);
      this.accountId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'accountId' field has been set.
      * @return True if the 'accountId' field has been set, false otherwise.
      */
    public boolean hasAccountId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'accountId' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder clearAccountId() {
      accountId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'type' field.
      * @return The value.
      */
    public java.lang.String getType() {
      return type;
    }


    /**
      * Sets the value of the 'type' field.
      * @param value The value of 'type'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder setType(java.lang.String value) {
      validate(fields()[1], value);
      this.type = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'type' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder clearType() {
      type = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'accountState' field.
      * @return The value.
      */
    public java.lang.String getAccountState() {
      return accountState;
    }


    /**
      * Sets the value of the 'accountState' field.
      * @param value The value of 'accountState'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder setAccountState(java.lang.String value) {
      validate(fields()[2], value);
      this.accountState = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'accountState' field has been set.
      * @return True if the 'accountState' field has been set, false otherwise.
      */
    public boolean hasAccountState() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'accountState' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder clearAccountState() {
      accountState = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'balance' field.
      * @return The value.
      */
    public java.math.BigDecimal getBalance() {
      return balance;
    }


    /**
      * Sets the value of the 'balance' field.
      * @param value The value of 'balance'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder setBalance(java.math.BigDecimal value) {
      validate(fields()[3], value);
      this.balance = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'balance' field has been set.
      * @return True if the 'balance' field has been set, false otherwise.
      */
    public boolean hasBalance() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'balance' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder clearBalance() {
      balance = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'createdAt' field.
      * @return The value.
      */
    public java.time.Instant getCreatedAt() {
      return createdAt;
    }


    /**
      * Sets the value of the 'createdAt' field.
      * @param value The value of 'createdAt'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder setCreatedAt(java.time.Instant value) {
      validate(fields()[4], value);
      this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'createdAt' field has been set.
      * @return True if the 'createdAt' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'createdAt' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder clearCreatedAt() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'overdraft' field.
      * @return The value.
      */
    public double getOverdraft() {
      return overdraft;
    }


    /**
      * Sets the value of the 'overdraft' field.
      * @param value The value of 'overdraft'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder setOverdraft(double value) {
      validate(fields()[5], value);
      this.overdraft = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'overdraft' field has been set.
      * @return True if the 'overdraft' field has been set, false otherwise.
      */
    public boolean hasOverdraft() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'overdraft' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder clearOverdraft() {
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'interestRate' field.
      * @return The value.
      */
    public double getInterestRate() {
      return interestRate;
    }


    /**
      * Sets the value of the 'interestRate' field.
      * @param value The value of 'interestRate'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder setInterestRate(double value) {
      validate(fields()[6], value);
      this.interestRate = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'interestRate' field has been set.
      * @return True if the 'interestRate' field has been set, false otherwise.
      */
    public boolean hasInterestRate() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'interestRate' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder clearInterestRate() {
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'customer' field.
      * @return The value.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer getCustomer() {
      return customer;
    }


    /**
      * Sets the value of the 'customer' field.
      * @param value The value of 'customer'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder setCustomer(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer value) {
      validate(fields()[7], value);
      this.customerBuilder = null;
      this.customer = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'customer' field has been set.
      * @return True if the 'customer' field has been set, false otherwise.
      */
    public boolean hasCustomer() {
      return fieldSetFlags()[7];
    }

    /**
     * Gets the Builder instance for the 'customer' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer.Builder getCustomerBuilder() {
      if (customerBuilder == null) {
        if (hasCustomer()) {
          setCustomerBuilder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer.newBuilder(customer));
        } else {
          setCustomerBuilder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer.newBuilder());
        }
      }
      return customerBuilder;
    }

    /**
     * Sets the Builder instance for the 'customer' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder setCustomerBuilder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer.Builder value) {
      clearCustomer();
      customerBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'customer' field has an active Builder instance
     * @return True if the 'customer' field has an active Builder instance
     */
    public boolean hasCustomerBuilder() {
      return customerBuilder != null;
    }

    /**
      * Clears the value of the 'customer' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount.Builder clearCustomer() {
      customer = null;
      customerBuilder = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BankAccount build() {
      try {
        BankAccount record = new BankAccount();
        record.accountId = fieldSetFlags()[0] ? this.accountId : (java.lang.String) defaultValue(fields()[0]);
        record.type = fieldSetFlags()[1] ? this.type : (java.lang.String) defaultValue(fields()[1]);
        record.accountState = fieldSetFlags()[2] ? this.accountState : (java.lang.String) defaultValue(fields()[2]);
        record.balance = fieldSetFlags()[3] ? this.balance : (java.math.BigDecimal) defaultValue(fields()[3]);
        record.createdAt = fieldSetFlags()[4] ? this.createdAt : (java.time.Instant) defaultValue(fields()[4]);
        record.overdraft = fieldSetFlags()[5] ? this.overdraft : (java.lang.Double) defaultValue(fields()[5]);
        record.interestRate = fieldSetFlags()[6] ? this.interestRate : (java.lang.Double) defaultValue(fields()[6]);
        if (customerBuilder != null) {
          try {
            record.customer = this.customerBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("customer"));
            throw e;
          }
        } else {
          record.customer = fieldSetFlags()[7] ? this.customer : (com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer) defaultValue(fields()[7]);
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
  private static final org.apache.avro.io.DatumWriter<BankAccount>
    WRITER$ = (org.apache.avro.io.DatumWriter<BankAccount>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<BankAccount>
    READER$ = (org.apache.avro.io.DatumReader<BankAccount>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}









