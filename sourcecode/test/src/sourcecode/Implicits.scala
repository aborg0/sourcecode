package sourcecode

object Implicits {
  def implicitRun() = {
    val name = implicitly[sourcecode.Name]
    assert(name.value == "name")

    val fullName = implicitly[sourcecode.FullName]
    assert(fullName.value == "sourcecode.Implicits.fullName")

    val enclosing = implicitly[sourcecode.Enclosing]
    assert(enclosing.value == "sourcecode.Implicits.implicitRun enclosing")

    val pkg = implicitly[sourcecode.Pkg]
    assert(pkg.value == "sourcecode")

    val file = implicitly[sourcecode.File]
    assert(file.value.endsWith("/sourcecode/Implicits.scala"))

    val fileName = implicitly[sourcecode.FileName]
    assert(fileName.value == "Implicits.scala")

    val line = implicitly[sourcecode.Line]
    assert(line.value == 23)

    lazy val myLazy = {
      trait Bar{
        val name = implicitly[sourcecode.Name]
        assert(name.value == "name")

        val fullName = implicitly[sourcecode.FullName]
        assert(fullName.value == "sourcecode.Implicits.Bar.fullName")

        val file = implicitly[sourcecode.File]
        assert(file.value.endsWith("/sourcecode/Implicits.scala"))

        val fileName = implicitly[sourcecode.FileName]
        assert(fileName.value == "Implicits.scala")

        val line = implicitly[sourcecode.Line]
        assert(line.value == 40)

        val enclosing = implicitly[sourcecode.Enclosing]
        assert(
          (enclosing.value == "sourcecode.Implicits.implicitRun myLazy$lzy Bar#enclosing") ||
          (enclosing.value == "sourcecode.Implicits.implicitRun myLazy Bar#enclosing") // encoding changed in Scala 2.12
        )
      }
      val b = new Bar{}
    }
    myLazy
  }
}
