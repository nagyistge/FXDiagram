package de.fxdiagram.pde;

import com.google.common.base.Objects;
import de.fxdiagram.annotations.properties.ModelNode;
import de.fxdiagram.core.model.ModelElementImpl;
import de.fxdiagram.eclipse.mapping.AbstractMappedElementDescriptor;
import de.fxdiagram.pde.BundleDescriptorProvider;
import de.fxdiagram.pde.BundleUtil;
import org.apache.log4j.Logger;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.internal.ui.editor.plugin.ManifestEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.osgi.framework.Version;

@ModelNode
@SuppressWarnings("all")
public class BundleDescriptor extends AbstractMappedElementDescriptor<BundleDescription> {
  private final static Logger LOG = Logger.getLogger(BundleDescriptor.class);
  
  public BundleDescriptor(final String symbolicName, final String version, final String mappingConfigID, final String mappingID, final BundleDescriptorProvider provider) {
    super(((symbolicName + "#") + version), symbolicName, mappingConfigID, mappingID, provider);
  }
  
  @Override
  public <U extends Object> U withDomainObject(final Function1<? super BundleDescription, ? extends U> lambda) {
    U _xblockexpression = null;
    {
      String _symbolicName = this.getSymbolicName();
      String _version = this.getVersion();
      final BundleDescription bundle = BundleUtil.findBundle(_symbolicName, _version);
      U _xifexpression = null;
      boolean _notEquals = (!Objects.equal(bundle, null));
      if (_notEquals) {
        _xifexpression = lambda.apply(bundle);
      } else {
        Object _xblockexpression_1 = null;
        {
          BundleDescriptor.LOG.warn(("Invalid BundleDescriptor " + this));
          _xblockexpression_1 = null;
        }
        _xifexpression = ((U)_xblockexpression_1);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public <U extends Object> U withPlugin(final Function1<? super IPluginModelBase, ? extends U> lambda) {
    U _xblockexpression = null;
    {
      String _symbolicName = this.getSymbolicName();
      String _version = this.getVersion();
      final IPluginModelBase plugin = PluginRegistry.findModel(_symbolicName, _version, IMatchRules.PERFECT, null);
      U _xifexpression = null;
      boolean _notEquals = (!Objects.equal(plugin, null));
      if (_notEquals) {
        _xifexpression = lambda.apply(plugin);
      } else {
        Object _xblockexpression_1 = null;
        {
          BundleDescriptor.LOG.warn(("Invalid BundleDescriptor " + this));
          _xblockexpression_1 = null;
        }
        _xifexpression = ((U)_xblockexpression_1);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public String getSymbolicName() {
    String _id = this.getId();
    String[] _split = _id.split("#");
    return IterableExtensions.<String>head(((Iterable<String>)Conversions.doWrapArray(_split)));
  }
  
  public String getVersion() {
    String _id = this.getId();
    String[] _split = _id.split("#");
    return IterableExtensions.<String>last(((Iterable<String>)Conversions.doWrapArray(_split)));
  }
  
  @Override
  public IEditorPart openInEditor(final boolean select) {
    final Function1<BundleDescription, IEditorPart> _function = (BundleDescription it) -> {
      IEditorPart _xblockexpression = null;
      {
        String _symbolicName = it.getSymbolicName();
        Version _version = it.getVersion();
        String _string = _version.toString();
        final IPluginModelBase plugin = PluginRegistry.findModel(_symbolicName, _string, IMatchRules.PERFECT, null);
        _xblockexpression = ManifestEditor.openPluginEditor(plugin);
      }
      return _xblockexpression;
    };
    return this.<IEditorPart>withDomainObject(_function);
  }
  
  /**
   * Automatically generated by @ModelNode. Needed for deserialization.
   */
  public BundleDescriptor() {
  }
  
  public void populate(final ModelElementImpl modelElement) {
    super.populate(modelElement);
  }
}
