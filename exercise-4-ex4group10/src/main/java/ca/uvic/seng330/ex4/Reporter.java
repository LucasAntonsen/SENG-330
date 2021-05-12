package ca.uvic.seng330.ex4;

import java.util.Objects;

/**
 * A Reporter represents the information of the user who has
 * seen a pod of whales. The id will eventually be generated when a
 * master list of all Reporters is maintained.
 */
public class Reporter implements Comparable<Reporter>{
  private final int id;
  private String name;
  private String telephone;
  private String email;
  private String address;
  private Type type = Type.CITIZEN;

  /**
   * Constructor for advanced instances of Reporter.
   *
   * @param id The system generated ID of the Reporter
   * @param name The name of the Reporter
   * @param telephone The telephone number of the reported as a String
   * @param email the email of the reporter
   * @param address the address of a reporter
   * @param type the Type of the Reporter
   */
  public Reporter(int id, String name, String telephone, String email, String address, Type type) {
    this(id, name, telephone, email, address);
    setType(type);
  }

  /**
   * Constructor for most instances if Reporter.
   *
   * @param id The system generated ID of the Reporter
   * @param name The name of the Reporter
   * @param telephone The telephone number of the reported as a String
   * @param email the email of the reporter
   * @param address the address of a reporter
   */
  public Reporter(int id, String name, String telephone, String email, String address) {
    this.id = id;
    setName(name);
    setTelephone(telephone);
    setEmail(email);
    setAddress(address);
  }


  /**
   * Getters
   */

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = Objects.requireNonNullElse(email, "");
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = Objects.requireNonNullElse(name, "");
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = Objects.requireNonNullElse(address, "");
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = Objects.requireNonNullElse(telephone, "");
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = Objects.requireNonNullElse(type, Type.CITIZEN);
  }

  @Override
  public String toString() {
    String str = "";
    str += "\t name:" + name;
    str += "\t telephone:" + telephone;
    str += "\t email:" + email;
    return (str);
  }

  @Override
  public int compareTo(Reporter o) {
    return name.compareTo(o.name);
  }


  /**
   * Type represents the qualifications of the Reporter.
   * Eventually, actions will be restricted based on the Type of the user.
   * For example, verifying an observation would happen only be scientists.
   */
  public enum Type {
    SCIENTIST,
    STUDENT,
    CITIZEN
  }
}
