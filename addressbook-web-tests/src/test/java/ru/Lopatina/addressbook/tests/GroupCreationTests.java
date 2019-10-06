package ru.Lopatina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.GroupData;
import ru.Lopatina.addressbook.model.TestBase;

import java.util.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().GroupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData("test1", null, null);
    app.group().create(group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Comparator<? super GroupData> ById = ((g1, g2) -> Integer.compare(g1.getId(), g2.getId()));
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);
  }

}
