由于GWT对XML解析的支持并不够，以及并没有提供java对象的反射，因此EMF对象的序列化/反序列化是一个世界难题。

幸运的是，gwt-html5-graph项目（ http://code.google.com/p/gwt-html5-graph/ ）成功的解决了此问题。

我们来首先看一下gwt-html5-graph中是如何将EMF对象存储为XMI的：

1、假设我们以及创建了一个EMF对象company。

2、首先我们先按照EMF通常的步骤将其存储到Resoure中：

> XMIResourceImpl resource = new XMIResourceImpl();
> resource.getContents().add(company);
> GWTOutputStream bs = new GWTOutputStream();
> > try {
> > > HashMap options = new HashMap();
> > > options.put(XMIResource.OPTION\_ENCODING, "UTF-8");
> > > resource.save(bs, options);

> > } catch (IOException e) {
> > > e.printStackTrace();

> > }



3、事实上GWT并不支持客户端的OutputStream。但是gwt-html5-graph提供了一个新的类GWTOutputStream()用以支持输出流。

4、转换输出流为字符串：String xmi = new String(bs.toString())。此时我们完成了EMF对象的序列化。

下面我们从这个字符串中反序列化，解析出EMF对象：

1、按照EMF通常的步骤加载XML：

ResourceSetImpl rs = new ResourceSetImpl();
XMIResourceImpl r = new XMIResourceImpl();
rs.getResources().add(r);
r.getResourceSet().getPackageRegistry()

> .put(companyPackage.getNsURI(), companyPackage);
ByteArrayInputStream is = new ByteArrayInputStream(bs.toString()
> .getBytes());
r.load(is, null);

2、从资源中获取EMF对象：resource.getContents().get(0)