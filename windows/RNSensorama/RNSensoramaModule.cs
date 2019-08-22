using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Sensorama.RNSensorama
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNSensoramaModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNSensoramaModule"/>.
        /// </summary>
        internal RNSensoramaModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNSensorama";
            }
        }
    }
}
