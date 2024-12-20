/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class CurrentAccountEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -1633311934400106788L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"CurrentAccountEvent\",\"namespace\":\"com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans\",\"fields\":[{\"name\":\"accountId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"accountState\",\"type\":{\"type\":\"enum\",\"name\":\"AccountState\",\"symbols\":[\"CREATED\",\"ACTIVE\",\"SUSPENDED\"]}},{\"name\":\"balance\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":7,\"scale\":3}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}},{\"name\":\"overdraft\",\"type\":\"double\"},{\"name\":\"customer\",\"type\":{\"type\":\"record\",\"name\":\"Customer\",\"namespace\":\"com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer\",\"fields\":[{\"name\":\"customerId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"firstname\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"lastname\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"email\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"customerState\",\"type\":{\"type\":\"enum\",\"name\":\"CustomerState\",\"symbols\":[\"ACTIVE\",\"ARCHIVE\"]}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}}]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.UUIDConversion());
    MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
  }

  private static final BinaryMessageEncoder<CurrentAccountEvent> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<CurrentAccountEvent> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<CurrentAccountEvent> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<CurrentAccountEvent> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<CurrentAccountEvent> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this CurrentAccountEvent to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a CurrentAccountEvent from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a CurrentAccountEvent instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static CurrentAccountEvent fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.util.UUID accountId;
  private com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.AccountState accountState;
  private java.nio.ByteBuffer balance;
  private java.time.Instant createdAt;
  private double overdraft;
  private com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer customer;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public CurrentAccountEvent() {}

  /**
   * All-args constructor.
   * @param accountId The new value for accountId
   * @param accountState The new value for accountState
   * @param balance The new value for balance
   * @param createdAt The new value for createdAt
   * @param overdraft The new value for overdraft
   * @param customer The new value for customer
   */
  public CurrentAccountEvent(java.util.UUID accountId, com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.AccountState accountState, java.nio.ByteBuffer balance, java.time.Instant createdAt, java.lang.Double overdraft, com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer customer) {
    this.accountId = accountId;
    this.accountState = accountState;
    this.balance = balance;
    this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
    this.overdraft = overdraft;
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
    case 1: return accountState;
    case 2: return balance;
    case 3: return createdAt;
    case 4: return overdraft;
    case 5: return customer;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      new org.apache.avro.Conversions.UUIDConversion(),
      null,
      null,
      new org.apache.avro.data.TimeConversions.TimestampMillisConversion(),
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
    case 0: accountId = (java.util.UUID)value$; break;
    case 1: accountState = (com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.AccountState)value$; break;
    case 2: balance = (java.nio.ByteBuffer)value$; break;
    case 3: createdAt = (java.time.Instant)value$; break;
    case 4: overdraft = (java.lang.Double)value$; break;
    case 5: customer = (com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'accountId' field.
   * @return The value of the 'accountId' field.
   */
  public java.util.UUID getAccountId() {
    return accountId;
  }


  /**
   * Sets the value of the 'accountId' field.
   * @param value the value to set.
   */
  public void setAccountId(java.util.UUID value) {
    this.accountId = value;
  }

  /**
   * Gets the value of the 'accountState' field.
   * @return The value of the 'accountState' field.
   */
  public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.AccountState getAccountState() {
    return accountState;
  }


  /**
   * Sets the value of the 'accountState' field.
   * @param value the value to set.
   */
  public void setAccountState(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.AccountState value) {
    this.accountState = value;
  }

  /**
   * Gets the value of the 'balance' field.
   * @return The value of the 'balance' field.
   */
  public java.nio.ByteBuffer getBalance() {
    return balance;
  }


  /**
   * Sets the value of the 'balance' field.
   * @param value the value to set.
   */
  public void setBalance(java.nio.ByteBuffer value) {
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
   * Gets the value of the 'customer' field.
   * @return The value of the 'customer' field.
   */
  public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer getCustomer() {
    return customer;
  }


  /**
   * Sets the value of the 'customer' field.
   * @param value the value to set.
   */
  public void setCustomer(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer value) {
    this.customer = value;
  }

  /**
   * Creates a new CurrentAccountEvent RecordBuilder.
   * @return A new CurrentAccountEvent RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder newBuilder() {
    return new com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder();
  }

  /**
   * Creates a new CurrentAccountEvent RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new CurrentAccountEvent RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder newBuilder(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder other) {
    if (other == null) {
      return new com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder();
    } else {
      return new com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder(other);
    }
  }

  /**
   * Creates a new CurrentAccountEvent RecordBuilder by copying an existing CurrentAccountEvent instance.
   * @param other The existing instance to copy.
   * @return A new CurrentAccountEvent RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder newBuilder(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent other) {
    if (other == null) {
      return new com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder();
    } else {
      return new com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder(other);
    }
  }

  /**
   * RecordBuilder for CurrentAccountEvent instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<CurrentAccountEvent>
    implements org.apache.avro.data.RecordBuilder<CurrentAccountEvent> {

    private java.util.UUID accountId;
    private com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.AccountState accountState;
    private java.nio.ByteBuffer balance;
    private java.time.Instant createdAt;
    private double overdraft;
    private com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer customer;
    private com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer.Builder customerBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.accountId)) {
        this.accountId = data().deepCopy(fields()[0].schema(), other.accountId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.accountState)) {
        this.accountState = data().deepCopy(fields()[1].schema(), other.accountState);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.balance)) {
        this.balance = data().deepCopy(fields()[2].schema(), other.balance);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[3].schema(), other.createdAt);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.overdraft)) {
        this.overdraft = data().deepCopy(fields()[4].schema(), other.overdraft);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.customer)) {
        this.customer = data().deepCopy(fields()[5].schema(), other.customer);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (other.hasCustomerBuilder()) {
        this.customerBuilder = com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer.newBuilder(other.getCustomerBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing CurrentAccountEvent instance
     * @param other The existing instance to copy.
     */
    private Builder(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.accountId)) {
        this.accountId = data().deepCopy(fields()[0].schema(), other.accountId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.accountState)) {
        this.accountState = data().deepCopy(fields()[1].schema(), other.accountState);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.balance)) {
        this.balance = data().deepCopy(fields()[2].schema(), other.balance);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[3].schema(), other.createdAt);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.overdraft)) {
        this.overdraft = data().deepCopy(fields()[4].schema(), other.overdraft);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.customer)) {
        this.customer = data().deepCopy(fields()[5].schema(), other.customer);
        fieldSetFlags()[5] = true;
      }
      this.customerBuilder = null;
    }

    /**
      * Gets the value of the 'accountId' field.
      * @return The value.
      */
    public java.util.UUID getAccountId() {
      return accountId;
    }


    /**
      * Sets the value of the 'accountId' field.
      * @param value The value of 'accountId'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder setAccountId(java.util.UUID value) {
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
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder clearAccountId() {
      accountId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'accountState' field.
      * @return The value.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.AccountState getAccountState() {
      return accountState;
    }


    /**
      * Sets the value of the 'accountState' field.
      * @param value The value of 'accountState'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder setAccountState(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.AccountState value) {
      validate(fields()[1], value);
      this.accountState = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'accountState' field has been set.
      * @return True if the 'accountState' field has been set, false otherwise.
      */
    public boolean hasAccountState() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'accountState' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder clearAccountState() {
      accountState = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'balance' field.
      * @return The value.
      */
    public java.nio.ByteBuffer getBalance() {
      return balance;
    }


    /**
      * Sets the value of the 'balance' field.
      * @param value The value of 'balance'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder setBalance(java.nio.ByteBuffer value) {
      validate(fields()[2], value);
      this.balance = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'balance' field has been set.
      * @return True if the 'balance' field has been set, false otherwise.
      */
    public boolean hasBalance() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'balance' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder clearBalance() {
      balance = null;
      fieldSetFlags()[2] = false;
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
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder setCreatedAt(java.time.Instant value) {
      validate(fields()[3], value);
      this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'createdAt' field has been set.
      * @return True if the 'createdAt' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'createdAt' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder clearCreatedAt() {
      fieldSetFlags()[3] = false;
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
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder setOverdraft(double value) {
      validate(fields()[4], value);
      this.overdraft = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'overdraft' field has been set.
      * @return True if the 'overdraft' field has been set, false otherwise.
      */
    public boolean hasOverdraft() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'overdraft' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder clearOverdraft() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'customer' field.
      * @return The value.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer getCustomer() {
      return customer;
    }


    /**
      * Sets the value of the 'customer' field.
      * @param value The value of 'customer'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder setCustomer(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer value) {
      validate(fields()[5], value);
      this.customerBuilder = null;
      this.customer = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'customer' field has been set.
      * @return True if the 'customer' field has been set, false otherwise.
      */
    public boolean hasCustomer() {
      return fieldSetFlags()[5];
    }

    /**
     * Gets the Builder instance for the 'customer' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer.Builder getCustomerBuilder() {
      if (customerBuilder == null) {
        if (hasCustomer()) {
          setCustomerBuilder(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer.newBuilder(customer));
        } else {
          setCustomerBuilder(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer.newBuilder());
        }
      }
      return customerBuilder;
    }

    /**
     * Sets the Builder instance for the 'customer' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder setCustomerBuilder(com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer.Builder value) {
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
    public com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.CurrentAccountEvent.Builder clearCustomer() {
      customer = null;
      customerBuilder = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CurrentAccountEvent build() {
      try {
        CurrentAccountEvent record = new CurrentAccountEvent();
        record.accountId = fieldSetFlags()[0] ? this.accountId : (java.util.UUID) defaultValue(fields()[0]);
        record.accountState = fieldSetFlags()[1] ? this.accountState : (com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.AccountState) defaultValue(fields()[1]);
        record.balance = fieldSetFlags()[2] ? this.balance : (java.nio.ByteBuffer) defaultValue(fields()[2]);
        record.createdAt = fieldSetFlags()[3] ? this.createdAt : (java.time.Instant) defaultValue(fields()[3]);
        record.overdraft = fieldSetFlags()[4] ? this.overdraft : (java.lang.Double) defaultValue(fields()[4]);
        if (customerBuilder != null) {
          try {
            record.customer = this.customerBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("customer"));
            throw e;
          }
        } else {
          record.customer = fieldSetFlags()[5] ? this.customer : (com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.Customer) defaultValue(fields()[5]);
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
  private static final org.apache.avro.io.DatumWriter<CurrentAccountEvent>
    WRITER$ = (org.apache.avro.io.DatumWriter<CurrentAccountEvent>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<CurrentAccountEvent>
    READER$ = (org.apache.avro.io.DatumReader<CurrentAccountEvent>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










